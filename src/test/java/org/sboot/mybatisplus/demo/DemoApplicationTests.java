package org.sboot.mybatisplus.demo;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.sboot.mybatisplus.demo.mapper.ProductMapper;
import org.sboot.mybatisplus.demo.mapper.UserMapper;
import org.sboot.mybatisplus.demo.pojo.Product;
import org.sboot.mybatisplus.demo.pojo.User;
import org.sboot.mybatisplus.demo.pojoenum.SexEnum;
import org.sboot.mybatisplus.demo.service.ProductService;
import org.sboot.mybatisplus.demo.service.UserService;
import org.sboot.mybatisplus.demo.service.impl.ProductServiceImpl;
import org.sboot.mybatisplus.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductService productService;


    @Test
    public void test1(){
        List<User> users = userMapper.selectByMap(null);
        users.forEach(System.out::println);
    }

    /**
     * 根据条件查询
     */

    @Test
    public void testSelect(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","Jone");
        map.put("age",18);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    /**
     * 根据多个id查询查询多条记录
     */
    @Test
    public void test2(){
        Collection<Integer> collection = new ArrayList<>();
        collection.add(1);
        collection.add(2);
        collection.add(3);
        //SELECT id,name,age,email,is_deleted,sex FROM user WHERE id IN ( ? , ? , ? ) AND is_deleted=0
        List<User> users = userMapper.selectBatchIds(collection);
        users.forEach(System.out::println);
    }
    /**
     * 条件拼接查询
     */
    @Test
    public void test5(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","lixiang").between("age",10,30);
        //SELECT id,name,age,email,is_deleted,sex FROM user
        // WHERE is_deleted=0 AND (name LIKE 'lixiang' AND age BETWEEN 10 AND 30)
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }

    /**插入语句，并返回ID
     *
     */
    @Test
    public void test3(){
        User user = new User();
        user.setAge(25);
        user.setEmail("lixiang.qinbo@gmail.com");
        user.setName("lixiang.qinbo");
        userMapper.insert(user);
        System.out.println(user.getId());
    }

    /**
     * 删除(当有逻辑删除字段时候就是逻辑删除既修改)
     */
    @Test
    public void test4(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","lixiang.qinbo");
        map.put("email","lixiang.qinbo@gmail.com");
        //UPDATE user SET is_deleted=1 WHERE name = 'lixiang.qinbo' AND email = 'lixiang.qinbo@gmail.com' AND is_deleted=0
        userMapper.deleteByMap(map);
    }

    /**
     * 根据条件选怎是否拼接 语句
     */
    @Test
    public void test6(){
        String name ="lixiang";
        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
        //根据条件选怎是否拼接 语句
        //SELECT id,name,age,email,is_deleted,sex FROM user WHERE is_deleted=0 AND (name LIKE '%lixiang%')
        objectQueryWrapper.like(StringUtils.isNotBlank(name),"name",name);
        List<User> users = userMapper.selectList(objectQueryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 避免使用字符串表示字段，防止运行时错误
     * LambdaQueryWrapper
     */
    @Test
    public void test7(){
        String name = "j";
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        //User::getName 表示字段名字
        //查询name包含“j”的并且年龄大于25邮箱不为NULL
        //SELECT id,name,age,email,is_deleted,sex FROM user WHERE is_deleted=0 AND (name LIKE 'j' AND age > 25 AND email IS NOT NULL)
        wrapper.like(User::getName,name).gt(User::getAge,25).isNotNull(User::getEmail);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);

    }
    @Test
    public void test8(){
        String emil = null;
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        //先判断是否输入了emial 如果输入了就
        wrapper.set(User::getAge,19)
                .set(User::getEmail,"lixiang.qinbo@qq.com")
                .set(User::getName,"qinbo")
                .like(User::getName,"lixiang")
                .and(i->i.between(User::getAge,20,30));
        User user = new User();
        userMapper.update(user,wrapper);
    }

    /**
     * 分页插件
     */
    @Test
    public void test9(){
        //设置分页面参数
        Page<User> page1 = new Page<>(2,3);
        Page<User> page = userMapper.selectPage(page1, null);
        //查看分页数据
        List<User> records = page.getRecords();
        records.forEach(System.out::println);
        System.out.println("当前页："+page.getCurrent());
        System.out.println("每页显示的条数："+page.getSize());
        System.out.println("总记录数："+page.getTotal());
        System.out.println("总页数："+page.getPages());
        System.out.println("是否有上一页："+page.hasPrevious());
        System.out.println("是否有下一页："+page.hasNext());

    }

    /**
     * 自定义分页
     */
    @Test
    public void test(){
        Page<User> page1 = new Page<>(1,3);
//        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.like(User::getName,"")
//                .like(User::getEmail,"")
//                .like(User::getAge,25);
        User user = new User();
//        user.setName("qin");
//        user.setEmail("lixiang");
//        user.setAge(null);
        Page<User> page = userService.selectPageBySelf(page1, user);
        //查看分页数据
        List<User> records = page.getRecords();
        records.forEach(System.out::println);
        System.out.println("当前页："+page.getCurrent());
        System.out.println("每页显示的条数："+page.getSize());
        System.out.println("总记录数："+page.getTotal());
        System.out.println("总页数："+page.getPages());
        System.out.println("是否有上一页："+page.hasPrevious());
        System.out.println("是否有下一页："+page.hasNext());
    }

    /**
     * 测试乐观锁和悲观锁
     */
    @Test
    public void test11(){
        // 小李
        Product product = productMapper.selectById(1);
        System.out.println(product.getPrice());
        //小王
        Product product1 = productMapper.selectById(1);
        System.out.println(product1.getPrice());
        //小李修改价格 100+50
        product.setPrice(product.getPrice()+50);
        int result = productMapper.updateById(product);
        //小李也去修改价格100-70
        product1.setPrice(product1.getPrice()-70);
        int res = productMapper.updateById(product1);
        if (res!=1){
            Product product2 = new Product();
            product2 = productMapper.selectById(1);
            product2.setPrice(product2.getPrice()-70);
            productMapper.updateById(product2);
        }
    }
    /**
     * 通用枚举
     */
    @Test
    public void test12(){
        User user = new User();
        user.setAge(25);
        user.setEmail("fiyuhua@qq.com");
        user.setName("fuyuhua");
        user.setSex(SexEnum.FEMALE);
        userMapper.insert(user);
    }

    /**
     * 主从分离 多数据源
     */
    @Test
    public void test13(){
//        User user = userService.getById(1);
//        System.out.println(user.toString());
//        Product product = productService.getById(1);
//        System.out.println(product.toString());
        Product product1 = productMapper.selectById(1);
        System.out.println(product1.toString());
    }
}
