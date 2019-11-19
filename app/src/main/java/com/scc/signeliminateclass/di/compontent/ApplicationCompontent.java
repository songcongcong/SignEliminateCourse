package com.scc.signeliminateclass.di.compontent;



import com.scc.signeliminateclass.di.module.ApplicationModule;

import dagger.Component;

/**
 * 作者：宋聪聪 on 2019/6/18.
 */
@Component (modules = ApplicationModule.class) //作为桥梁，连接数据
public interface ApplicationCompontent {
}
