package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.entity.Menu;
import com.atguigu.crowd.service.api.MenuService;
import com.atguigu.crowd.utils.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author linlingde
 * @version 1.0
 * @className MenuHandler
 * @description menu
 * @date 2022/7/13 10:24
 **/

@Controller
public class MenuHandler {

    @Autowired
    private MenuService menuService;

    @ResponseBody
    @RequestMapping("/menu/get/menu/list.json")
    public ResultEntity<Menu> getWholeTree() {
        // 获取全部menu
        List<Menu> menuList = menuService.getAll();
        // 声明一个变量存储找到的根节点
        Menu rootMenu = null;
        Map<Integer, Menu> menuMap = new HashMap<>();
        // 遍历集合,将id作为键,id作为值
        for (Menu menu : menuList) {

            menuMap.put(menu.getId(), menu);

        }
        // 遍历menu集合,如果pid,说明它是父节点,
        for (Menu menu : menuList) {
            // 获取当前menu的pid
            Integer pid = menu.getPid();
            // 如果为空,说明是根节点
            if (pid == null) {
                rootMenu = menu;
                // 停止本次循环,开始下一次循环
                continue;
            }
            // 如果pid不为null,说明有爹
            Menu father = menuMap.get(pid);
            father.getChildren().add(menu);

            // 如果pid不为null,就帮它找爹
            // for (Menu maybeFather : menuList) {
            //    // 获取id
            //    Integer fatherId = maybeFather.getId();
            //    // 如果id等于pid
            //    if (Objects.equals(fatherId, pid)) {
            //        // 将其添加到集合
            //        maybeFather.getChildrenMenuList().add(menu);
            //        // 找到爹了,结束当前循环
            //        break;
            //    }
            //}

        }

        // 将组装好的树形结构返回给浏览器
        return ResultEntity.successWithData(rootMenu);
    }
}
