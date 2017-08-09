建议在UE中打开

客户信息管理系统案例
		1.先要环境搭建（建包，导包。tomcat搭建）
		2.建表
		3.根据表建立javabean；
		4.利用工厂类实现解耦；
					工厂类必须要有三步：
					(1)、接口
									|	做出CustomerDao接口与CustomerDaoImpl（在dao包中做）
									|	做出Service接口与ServiceImpl
					(2)、配置文件
									|在config-properties中分别配置出接口=接口的实现
									例子：CustomerDao=com.gus.dao.CustomerDaoImpl
												CustomerService=com.gus.service.CustomerServiceImpl
					(3)、创建工厂类
					
		5。
			1.添加客户
					index.jsp提供<添加客户>超链接
						--->addCust.jsp 添加客户的页面，提供表单，允许输入客户信息
						--->提交到AddCustServlet  1.校验数据/封装数据 2.调用service层的方法添加客户 3.重定向回到主页（应该到客户列表）；
						--->service中应该提供添加客户的方法：在这里应该做：检查客户名是否已经存在，如果存在则提示，如不存在则
								调用Dao添加客户						
						--->Dao中应该提供这么两个方法：根据用户名查找用户的方法，添加客户的方法
			2.查询客户列表
					index.jsp中提供<查询客户列表>超链接
						--->ListCustomerServlet,调用service中查询所有客户的方法
						--->service中调用dao查询所有客户的方法，查到数据后，将查到的数据存到request域中，请求转发至
						--->listCustomer.jsp页面展示
							service调用dao中的方法查询所有客户
							dao中查询所有用户
							listCustomer.jsp中遍历做展示
			3.修改客户信息(查询/修改)
					在客户信息列表页面，每一条记录后面都有个<修改>超链接（所谓的修改，每一个修改之前，必须有一个查询的过程）
					先去查找到当前客户的详细信息，展示在页面上 
					--->CustInfServlet调用service中的方法，找到当前客户信息，存入request域后，带到updateCust.jsp页面中显示
					--->updateCust.jsp显示客户信息，并允许做修改，修改完成一提交，提交到
					--->UpdateCustServlet中，封装数据/调用service中修改数据的方法
					--->service调用dao中的方法
					--->dao中提供修改客户信息的方法
			4.删除客户
					在客户信息的列表页面，每一条记录都有一个<删除>超链接
					--->DelCustServlet 获取要删除的客户的id，调用service方法中的删除客户的方法，请求转发到列表页面，
					--->service中删除客户的方法 调用Dao中的删除方法
					--->dao中有根据id删除客户的方法
			5.批量删除客户信息
					在客户列表之前添加一个复选框。选中后，可以删除
					--->BatchDelCustServlet，获取所有要删除的客户的id，调用service方法中的批量删除客户的方法作删除操作
					--->service中提供删除客户客户的方法，事务的管理
					--->dao中删除客户的方法
						*事务时属于service业务逻辑上面的东西，不是dao的 
			
						
不足：
		软件分层中，为了减少耦合，只属于某一层的东西就不要乱传，删除客户信息时，属于dao层次的connection什么的都跑出来了，还有
		SQlException也是属于dao层的东西，却在service层有所显现，所以在这个地方仍然造成耦合，所以在sercice层的batchDel方法写得
		非常不好。
		后边会有一个threadlocal本地线程的机制可以解决这个问题。
		
		
		
			6.条件查询客户信息
						在客户列表页面之前，提供一个查询的表单，允许通过用户名/性别/客户类型进行条件查询
						--->FindCustByCondServlet 将条件封装到javabean中，调用service中的条件查询客户的方法，将查询到的多有信息存入request域中，带到listCustomer.jsp页面中作展示
						--->Service层提供根据条件查询客户的方法
						--->Dao中条件查询客户的方法
			7.分页查询客户信息
						共xxx条记录   共xx页  首页 上一页 1 2 3 4 5 下一页 尾页
						为避免过于混乱，重新写一个分页查询的页面
						在index.jap中，提供一个<分页查询客户信息>超链接
						--->pageCustServlet获取要访问的页码以及每页显示多少条记录，调用service中的分页查询客户的方法查询客户，存入request域带到
								pageList.jsp页面进行展示
						--->service中提供分页查询的方法
						
						！！！物理分页
						在sql查询时，从数据库只检索分页需要的数据
						通常不同的数据库有着不同的物理分页语句
						mysql物理分页，采用limit关键字
						例如：检索11-20条 select * from user limit 10,10 ;
						-1.数据比较新
						-2.对内存的压力较小
						-3.对数据库访问较多
						
						
						逻辑分页
						在sql查询时，先从数据库检索出所有数据的结果集
						在程序内，通过逻辑语句获得分页需要的的数据
						例如: 检索11-20条 userList.subList(10,20);


						共xxx条记录   共xx页  首页 上一页 1 2 3 4 5 下一页 尾页
						上述功能的实现在代码案例中能够得到体现
						