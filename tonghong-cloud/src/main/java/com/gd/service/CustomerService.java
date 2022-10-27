package com.gd.service;

import com.gd.mapper.CustomerMapper;
import com.gd.model.Customer;
import com.gd.model.Pages;
import com.gd.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: CustomerService <br>
 * date: 2022-10-14 11:34 <br>
 * author: DaTao <br>
 * version: 1.0 <br>
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    public List<Customer> list(){
        return customerMapper.list();
    }

    public Pages<Teacher> getPage(Integer pageIndex, Integer pageSize){
        //起始条数
        Integer recordStart = (pageIndex-1)*pageSize;

        //获取data
        List<Customer> data = customerMapper.queryCourse(recordStart,pageSize);


        int totalRecord = customerMapper.getRecordCount();

        //计算总页数
        Integer totalPage = totalRecord%pageSize == 0 ? totalRecord/pageSize : totalRecord/pageSize + 1;

        //组装page对象
        Pages<Teacher> pages = new Pages(pageSize,totalPage,totalRecord,pageIndex,data);


        return pages;
    }


    public boolean check(Customer customer) {

        System.out.println("检查用户是否存在");
        System.out.println("检查结果"+customerMapper.check(customer));
        if (customerMapper.check(customer)==1){
            System.out.println("检查结果:存在该用户");
            return true;
        }
        return false;
    }

    public void add(Customer customer) {

        //先检查，再添加
        if (!check(customer)){
            System.out.println("用户不存在，可以添加");
            try{
                customerMapper.add(customer);

            }catch (Exception e){
                System.out.println(customer.getNickname()+"已经存在,不要重复保存");
                e.printStackTrace();
            }
        }else {
            System.out.println("用户存在，不可以添加");
        }

    }

    public void delete(Integer id) {
        customerMapper.delete(id);
    }

    public void fill(Customer customer) {


        customerMapper.fill(customer);


    }
    public void update(Customer customer) {

        customerMapper.update(customer);
    }


    public Customer login(Customer customer){
        System.out.println("正在登录"+customer.toString());
        Customer temp_customer = new Customer();
        if(customerMapper.checkuser(customer)==1){
            System.out.println("用户未使用,将填充");
            customerMapper.fill(customer);
            System.out.println("填充完成");
        }else {
            System.out.println("正在登录");
        }
        temp_customer = customerMapper.login(customer);
        return temp_customer;

    }



    public boolean checkuser(Customer customer) {

        System.out.println("检查用户是否使用");
        System.out.println("检查结果"+customerMapper.checkuser(customer));
        if (customerMapper.checkuser(customer)==1){
            System.out.println("检查结果:该用户未使用");
            return true;
        }
        return false;
    }





}
