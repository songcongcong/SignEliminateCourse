package com.scc.signeliminateclass.di.compontent;




import com.scc.signeliminateclass.MainActivity;
import com.scc.signeliminateclass.di.module.ActivityModule;
import com.scc.signeliminateclass.mvp.ui.SignInActivity;
import com.scc.signeliminateclass.mvp.ui.StartClassActivity;
import com.scc.signeliminateclass.mvp.ui.TestErrorActivity;
import com.scc.signeliminateclass.mvp.ui.UserErrorActivity;

import dagger.Component;

/**
 * 作者：宋聪聪 on 2019/6/18.
 */
@Component (modules = ActivityModule.class)
public interface ActivityCompontent {
    /**
     * inject
     * @param mainActivity mainActivity
     */
    void inject(MainActivity mainActivity);

    /**
     * inject
     * @param signInActivity signInActivity
     */
    void inject(SignInActivity signInActivity);

    /**
     * inject
     * @param activity activity
     */
    void inject(TestErrorActivity activity);

    /**
     * inject
     * @param userErrorActivity userErrorActivity
     */
    void inject(UserErrorActivity userErrorActivity);

    /**
     * inject
     * @param startClassActivity startClassActivity
     */
    void inject(StartClassActivity startClassActivity);
}
