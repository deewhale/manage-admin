package com.deewhale.manageadmin;

import com.deewhale.manageadmin.sys.domain.User;
import com.deewhale.manageadmin.sys.domain.test.BeanList;
import com.deewhale.manageadmin.sys.domain.test.XmlBean;
import com.deewhale.manageadmin.sys.mapper.UserMapper;
import com.thoughtworks.xstream.XStream;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class ManageAdminApplicationTests {

	@Test
	void contextLoads() {
	}

	@Resource
	private UserMapper userMapper;

	@Test
	public void testSelect() {
		System.out.println(("----- selectAll method test ------"));
		List<User> userList = userMapper.selectList(null);
		Assert.assertEquals(1, userList.size());
		userList.forEach(System.out::println);
	}

	@Test
	public void localDate(){
		LocalDateTime time = LocalDateTime.now();

		System.out.println(time);

	}


	@Test
	public void toXML(){
		XmlBean xmlBean = new XmlBean();
		xmlBean.setAge("12");
		xmlBean.setName("pepper");

		BeanList[] lists = new BeanList[3];
		lists[0] = new BeanList("0");
		lists[1] = new BeanList("1");
		lists[2] = new BeanList("2");

		xmlBean.setBeanLists(lists);
		XStream xStream = new XStream();

		xStream.alias("bean",XmlBean.class);
		xStream.alias("BeanList",BeanList.class);

		String result =  xStream.toXML(xmlBean);

		log.info(" result : \n {}",result);

	}

	@Test
	public void getXML(){

	}
}
