package com.famebuster.di.components;

import android.app.Application;

import com.famebuster.FameBusterApp;
import com.famebuster.di.modules.ActivityBuilderModule;
import com.famebuster.di.modules.AppModule;
import com.famebuster.di.modules.FragmentBuilderModule;
import com.famebuster.di.modules.LocalModule;
import com.famebuster.di.modules.NetworkModule;
import com.famebuster.di.modules.ViewModelModule;
import com.famebuster.di.modules.user.UserModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AppModule.class,
        AndroidSupportInjectionModule.class,
        ActivityBuilderModule.class,
        FragmentBuilderModule.class,
        NetworkModule.class,
        ViewModelModule.class,
        LocalModule.class,
        UserModule.class
   })
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(FameBusterApp fameBusterApp);
}
