package com.atguigu.crowd.service.api;import com.atguigu.crowd.entity.Admin;import com.github.pagehelper.PageInfo;import java.util.List;/** * @author linlingde * @version 1.0 * @interfaceName AdminService * @description AdminService * @date 2022/7/7 20:16 **/public interface AdminService {    public String saveAdmin(Admin admin);    List<Admin> getAll();    Admin getAdminByLoginAccount(String loginAcct, String userPassword);    PageInfo<Admin> getAdminPageInfo(String keyword, Integer pageNum, Integer pageSize);    void removeAdmin(Integer id);    Admin findAdminById(Integer id);    void updateAdmin(Admin admin);    void removeAdminRoleShip(Integer id);    void saveAdminRoleShip(Integer id, List<Integer> list);}