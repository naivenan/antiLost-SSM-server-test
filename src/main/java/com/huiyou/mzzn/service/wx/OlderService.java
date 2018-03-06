package com.huiyou.mzzn.service.wx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huiyou.mzzn.mapper.UserBindMapper;
import com.huiyou.mzzn.mapper.UserMapper;
import com.huiyou.mzzn.model.User;
import com.huiyou.mzzn.model.UserBind;
import com.huiyou.mzzn.model.UserBindExample;
import com.huiyou.mzzn.model.UserExample;
import com.huiyou.mzzn.pojo.WxUser;

@Service
public class OlderService {
	
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserBindMapper userBindMapper;
	
	public List<WxUser> list(int uid){
		List<WxUser> r = new ArrayList<>();
		try {
			UserBindExample example=new UserBindExample();
			example.setOrderByClause("create_time asc");
			example.or().andIsDelEqualTo(0).andBindUserIdEqualTo((long)uid);
			int count=userBindMapper.countByExample(example);
			List<UserBind> list;
			if (count==0) {
				list=Collections.emptyList();
			}else {
				list=userBindMapper.selectByExample(example);
				for (UserBind userBind : list) {
					User u=userMapper.selectByPrimaryKey(userBind.getUserId());
					if (u!=null) {
						WxUser wxUser = new WxUser();
						wxUser.setId(u.getId());
						wxUser.setType(u.getType());
						wxUser.setUser(u.getUsername());
						wxUser.setPassword(u.getPassword());
						wxUser.setName(u.getUsername());
						wxUser.setMphone(u.getMobile());
						wxUser.setBirthday(u.getBirthday());
						wxUser.setImgUrl(u.getHeadPic());
						r.add(wxUser);
					}
				}
			}
			
		} catch (Exception e) {
			System.err.println(e);
		}
		return r;
	}
	
	public int add(int uid,String user){
		int r = 0;
		try {
			UserExample example = new UserExample();
			example.createCriteria().andUsernameEqualTo(user);
			User u = userMapper.selectByExample(example).get(0);
			UserBind ub = new UserBind();
			ub.setUserId((long)u.getId());
			ub.setBindUserId((long)uid);
			userBindMapper.insertSelective(ub);
		} catch (Exception e) {
			System.err.println(e);
		}
		return r;
	}
	
	public List<Integer> delete(List<WxUser> list,int uid){
		List<Integer> r = new ArrayList<>();
		try {
			WxUser older = null;
			UserBind ub = new UserBind();
			ub.setIsDel(1);
			UserBindExample example = new UserBindExample();
			for(int i=0;i<list.size();i++){
				older = list.get(i);
				example.createCriteria().andUserIdEqualTo((long)older.getId())
					.andBindUserIdEqualTo((long)uid);
				userBindMapper.updateByExampleSelective(ub, example);
				example.clear();
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		return r;
	}
}
