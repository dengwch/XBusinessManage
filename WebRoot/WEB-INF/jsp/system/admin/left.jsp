<%
	String pathl = request.getContextPath();
	String basePathl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathl+"/";
%>
		<!-- 本页面涉及的js函数，都在head.jsp页面中     -->
		<div id="sidebar" class="menu-min">

				<div id="sidebar-shortcuts">

					<div id="sidebar-shortcuts-large">

<!-- 						<button class="btn btn-small btn-success" onclick="changeMenu();" title="切换菜单"><i class="icon-pencil"></i></button> -->

<!-- 						<button class="btn btn-small btn-info" title="UI实例" onclick="window.open('<%=basePathl%>static/UI_new');"><i class="icon-eye-open"></i></button> -->

<!-- 						<button class="btn btn-small btn-warning" title="数据字典" id="adminzidian" onclick="zidian();"><i class="icon-book"></i></button> -->
						
						<!-- <button class="btn btn-small btn-danger" title="菜单管理" id="adminmenu" onclick="menu();"><i class="icon-folder-open"></i></button> -->
						
					</div>

					<div id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span>

						<span class="btn btn-info"></span>

						<span class="btn btn-warning"></span>

						<span class="btn btn-danger"></span>
					</div>

				</div><!-- #sidebar-shortcuts -->


				<ul class="nav nav-list">

					<li class="active" id="fhindex">
					  <a href="main/index"><i class="icon-dashboard"></i><span>后台首页</span></a>
					</li>
				<!--<li id="lm_zfgl">
					  <a style="cursor:pointer;" class="dropdown-toggle" onclick="siMenu(null,'lm_zfgl','支付管理','paymanagement/list.do')">
						<i class="icon-desktop"></i>
						<span>支付管理</span>
						<b class="arrow icon-angle-down"></b>
					  </a>
				</li>
				-->
				<li id="lm_shxx">
					  <a style="cursor:pointer;" class="dropdown-toggle" onclick="siMenu(null,'lm_shxx','填写审核信息','businessuser/goEditbu.do')">
						<i class="icon-desktop"></i>
						<span>填写审核信息</span>
						<b class="arrow icon-angle-down"></b>
					  </a>
				</li>
				<li id="lm_spgl">
					  <a style="cursor:pointer;" class="dropdown-toggle" >
						<i class="icon-desktop"></i>
						<span>商品管理</span>
						<b class="arrow icon-angle-down"></b>
					  </a>
					  <ul class="submenu">
						  <li id="lm_spgl1">
						  <a style="cursor:pointer;" target="mainFrame"  onclick="siMenu('lm_spgl1','lm_spgl','商品列表','commodity/list.do')"><i class="icon-double-angle-right"></i>商品列表</a>
						  </li>
				  	  </ul>
				</li>
				<li id="lm_flgl">
					  <a style="cursor:pointer;" class="dropdown-toggle" >
						<i class="icon-desktop"></i>
						<span>分类管理</span>
						<b class="arrow icon-angle-down"></b>
					  </a>
					  <ul class="submenu">
						  <li id="lm_flgl1">
						  <a style="cursor:pointer;" target="mainFrame"  onclick="siMenu('lm_flgl1','lm_flgl','分类列表','classification/list.do')"><i class="icon-double-angle-right"></i>分类列表</a>
						  </li>
				  	  </ul>
				</li>
				<!--<li id="lm_ddgl">
					  <a style="cursor:pointer;" class="dropdown-toggle">
						<i class="icon-desktop"></i>
						<span>订单管理</span>
						<b class="arrow icon-angle-down"></b>
					  </a>
					  <ul class="submenu">
						  <li id="lm_ddgl1">
						  <a style="cursor:pointer;" target="mainFrame"  onclick="siMenu('lm_ddgl1','lm_ddgl','订单列表','ordermanger/list.do')"><i class="icon-double-angle-right"></i>订单列表</a>
						  </li>
						  <li id="lm_ddgl2">
						  <a style="cursor:pointer;" target="mainFrame"  onclick="siMenu('lm_ddgl2','lm_ddgl','待发货列表','ordermanger/waitSendList.do')"><i class="icon-double-angle-right"></i>待发货列表</a>
						  </li>
						  <li id="lm_ddgl3">
						  <a style="cursor:pointer;" target="mainFrame"  onclick="siMenu('lm_ddgl3','lm_ddgl','退货退款列表','ordermanger/returnList.do')"><i class="icon-double-angle-right"></i>退货退款列表</a>
						  </li>
				  	  </ul>
				</li>
				--><!--<li id="lm_yjfk" >
					  <a style="cursor:pointer;" class="dropdown-toggle" >
						<i class="icon-desktop"></i>
						<span>意见反馈管理 </span>
						<b class="arrow icon-angle-down"></b>
					  </a>
					  <ul class="submenu">
						  <li id="lm_yjfk1">
						  <a style="cursor:pointer;" target="mainFrame"  onclick="siMenu('lm_yjfk1','lm_yjfk','意见反馈列表','feedback/list.do')"><i class="icon-double-angle-right"></i>意见反馈列表</a>
						  </li>
				  	  </ul>
				
				</li>
				
				
				--><!--<li id="lm_hdgl">
					  <a style="cursor:pointer;" class="dropdown-toggle" >
						<i class="icon-desktop"></i>
						<span>商家活动管理</span>
						<b class="arrow icon-angle-down"></b>
					  </a>
					  <ul class="submenu">
						  <li id="lm_hdgl1">
						  <a style="cursor:pointer;" target="mainFrame"  onclick="siMenu('lm_hdgl1','lm_hdgl','活动列表','bussinessactivity/list.do')"><i class="icon-double-angle-right"></i>活动列表</a>
						  </li>
				  	  </ul>
				</li>
				-->
				<li id="lm_hdgl">
					  <a style="cursor:pointer;" class="dropdown-toggle" >
						<i class="icon-desktop"></i>
						<span>优惠信息</span>
						<b class="arrow icon-angle-down"></b>
					  </a>
					  <ul class="submenu">
						  <li id="lm_hdgl1">
						  <a style="cursor:pointer;" target="mainFrame"  onclick="siMenu('lm_hdgl1','lm_hdgl','优惠列表','bussinessactivity/list.do')"><i class="icon-double-angle-right"></i>优惠列表</a>
						  </li>
				  	  </ul>
				</li>
				
				
				<li id="lm_sjxx">
					  <a style="cursor:pointer;" class="dropdown-toggle" onclick="siMenu(null,'lm_sjxx','填写商家信息','businessuser/goEditxx.do')">
						<i class="icon-desktop"></i>
						<span>填写商家信息</span>
						<b class="arrow icon-angle-down"></b>
					  </a>
				</li>
				<li id="lm_hykgl">
					  <a style="cursor:pointer;" class="dropdown-toggle" onclick="siMenu(null,'lm_hykgl','会员卡管理','membershipcard/list.do')">
						<i class="icon-desktop"></i>
						<span>会员卡管理</span>
						<b class="arrow icon-angle-down"></b>
					  </a>
				</li>
				<!--<li id="lm_fxgl">
					  <a style="cursor:pointer;" class="dropdown-toggle" >
						<i class="icon-desktop"></i>
						<span>分销管理</span>
						<b class="arrow icon-angle-down"></b>
					  </a>
					  <ul class="submenu">
						  <li id="lm_fxgl1">
						  <a style="cursor:pointer;" target="mainFrame"  onclick="siMenu('lm_fxgl1','lm_fxgl','分销统计','distributioncount/list.do')"><i class="icon-double-angle-right"></i>分销统计</a>
						  </li>
				  	  </ul>
				</li>
				
				<li id="lm_xtsz">
					  <a style="cursor:pointer;" class="dropdown-toggle" >
						<i class="icon-desktop"></i>
						<span>系统设置</span>
						<b class="arrow icon-angle-down"></b>
					  </a>
					  <ul class="submenu">
						  <li id="lm_xtsz1">
						  <a style="cursor:pointer;" target="mainFrame"  onclick="siMenu('lm_xtsz1','lm_xtsz','物流模板设置','logistics/list.do')"><i class="icon-double-angle-right"></i>物流模板设置</a>
						  </li>
				  	  </ul>
				</li>
				<li id="lm_hygl">
					  <a style="cursor:pointer;" class="dropdown-toggle" >
						<i class="icon-desktop"></i>
						<span>会员管理</span>
						<b class="arrow icon-angle-down"></b>
					  </a>
					  <ul class="submenu">
						  <li id="lm_hygl1">
						  <a style="cursor:pointer;" target="mainFrame"  onclick="siMenu('lm_hygl1','lm_hygl','会员列表','vipmanger/list.do')"><i class="icon-double-angle-right"></i>会员列表</a>
						  </li>
				  	  </ul>
				</li>
				<li id="lm_sygl">
					  <a style="cursor:pointer;" class="dropdown-toggle" >
						<i class="icon-desktop"></i>
						<span>首页管理</span>
						<b class="arrow icon-angle-down"></b>
					  </a>
					  <ul class="submenu">
						  <li id="lm_sygl1">
						  <a style="cursor:pointer;" target="mainFrame"  onclick="siMenu('lm_sygl1','lm_sygl','轮播图管理','carousel/list.do')"><i class="icon-double-angle-right"></i>轮播图管理</a>
						  </li>
						  <li id="lm_sygl2">
						  <a style="cursor:pointer;" target="mainFrame"  onclick="siMenu('lm_sygl2','lm_sygl','LOGO设置','logo/list.do')"><i class="icon-double-angle-right"></i>LOGO设置</a>
						  </li>
				  	  </ul>
				</li>
			--><%-- <c:forEach items="${menuList}" var="menu">
				<c:if test="${menu.hasMenu}">
				<li id="lm${menu.MENU_ID }">
					  <a style="cursor:pointer;" class="dropdown-toggle" >
						<i class="${menu.MENU_ICON == null ? 'icon-desktop' : menu.MENU_ICON}"></i>
						<span>${menu.MENU_NAME }</span>
						<b class="arrow icon-angle-down"></b>
					  </a>
					  <ul class="submenu">
							<c:forEach items="${menu.subMenu}" var="sub">
								<c:if test="${sub.hasMenu}">
								<c:choose>
									<c:when test="${not empty sub.MENU_URL}">
									<li id="z${sub.MENU_ID }">
									<a style="cursor:pointer;" target="mainFrame"  onclick="siMenu('z${sub.MENU_ID }','lm${menu.MENU_ID }','${sub.MENU_NAME }','${sub.MENU_URL }')"><i class="icon-double-angle-right"></i>${sub.MENU_NAME }</a></li>
									</c:when>
									<c:otherwise>
									<li><a href="javascript:void(0);"><i class="icon-double-angle-right"></i>${sub.MENU_NAME }</a></li>
									</c:otherwise>
								</c:choose>
								</c:if>
							</c:forEach>
				  		</ul>
				</li>
				</c:if>
			</c:forEac--%>

				</ul><!--/.nav-list-->

				<div id="sidebar-collapse"><i class="icon-double-angle-left"></i></div>

			</div><!--/#sidebar-->

