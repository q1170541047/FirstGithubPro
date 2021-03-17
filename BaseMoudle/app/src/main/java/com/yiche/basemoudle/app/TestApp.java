package com.yiche.basemoudle.app;

import com.yiche.baselibrary.app.BaseApplicaticon;

import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.unit.Subunits;

/**
 * @Description: java类作用描述
 * @Author: yiche_li
 * @CreateDate: 2021/3/16 14:45
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/3/16 14:45
 */

public class TestApp extends BaseApplicaticon {
    @Override
    public void onCreate() {
        super.onCreate();
        configUnits();
    }
    /**
     * 屏幕适配
     */
    private void configUnits() {
        //AndroidAutoSize 默认开启对 dp 的支持, 调用 UnitsManager.setSupportDP(false); 可以关闭对 dp 的支持
        //主单位 dp 和 副单位可以同时开启的原因是, 对于旧项目中已经使用了 dp 进行布局的页面的兼容
        //让开发者的旧项目可以渐进式的从 dp 切换到副单位, 即新页面用副单位进行布局, 然后抽时间逐渐的将旧页面的布局单位从 dp 改为副单位
        //最后将 dp 全部改为副单位后, 再使用 UnitsManager.setSupportDP(false); 将 dp 的支持关闭, 彻底隔离修改 density 所造成的不良影响
        //如果项目完全使用副单位, 则可以直接以像素为单位填写 AndroidManifest 中需要填写的设计图尺寸, 不需再把像素转化为 dp
        AutoSizeConfig.getInstance().getUnitsManager()
                .setSupportDP(false)
                .setSupportSubunits(Subunits.MM);
    }

}
