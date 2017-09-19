package com.krt.core.shiro;

import com.krt.admin.system.service.ResService;
import com.krt.admin.system.service.UserService;
import com.krt.core.util.ShiroUtil;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**  
 * @Description: 自定义Realm
 * @date 2016年4月18日
 * @version 1.0
 */
public class ShiroRealm extends AuthorizingRealm {  
	
	@Resource
	private UserService userService;
	@Resource
	private ResService resService;
	
    /**
     * 为当前登录的Subject授予角色和权限  
     */
	@Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){  
    	//为当前用户设置角色和权限  
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();  
        //获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()  
        String currentUsername = (String)super.getAvailablePrincipal(principals);  
        Map user = userService.selectByUsername(currentUsername);  
        String roleCode = (String) user.get("roleCode");
        //为当前用户设置角色
        simpleAuthorInfo.addRole(roleCode);  
	    //获取角色权限
        List<Map> list = resService.selectRoleResList(roleCode);
        for(Map m:list){
        	String permission = m.get("permission")+"";
        	if(permission!=null && !"".equals(permission)){
        		simpleAuthorInfo.addStringPermission(permission);
        	}
        }
        return simpleAuthorInfo;  
    }


    /**
     * 验证当前登录的Subject
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
	@Override  
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {  
        //获取基于用户名和密码的令牌  
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的  
        //两个token的引用都是一样的
        UsernamePasswordToken token = (UsernamePasswordToken)authcToken;  
        System.out.println("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));  
        Map currentUser = userService.selectByUsername(token.getUsername());  
	    if(null != currentUser){  
	         AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(currentUser.get("username")+"", currentUser.get("password")+"", ""); 
	         ShiroUtil.setSession("currentUser", currentUser);
	         return authcInfo;  
	    }else{  
	    	  return null;  
	    }
    }

}