package com.scc.signeliminateclass.di.compontent;




import com.scc.signeliminateclass.MainActivity;
import com.scc.signeliminateclass.di.module.ActivityModule;
import com.scc.signeliminateclass.mvp.ui.SignInActivity;
import com.scc.signeliminateclass.mvp.ui.TestErrorActivity;
import com.scc.signeliminateclass.mvp.ui.UserErrorActivity;

import dagger.Component;

/**
 * 作者：宋聪聪 on 2019/6/18.
 */
@Component (modules = ActivityModule.class)
public interface ActivityCompontent {
    void inject(MainActivity mainActivity);
    void inject(SignInActivity signInActivity);
    void inject(TestErrorActivity activity);
    void inject(UserErrorActivity userErrorActivity);
}
