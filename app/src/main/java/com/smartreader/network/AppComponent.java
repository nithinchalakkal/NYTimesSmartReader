package com.smartreader.network;



import com.smartreader.activity.MainActivity;
import com.smartreader.fragment.ArticlesFragment;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {NetWorkModule.class, AppModule.class})
public interface AppComponent {

    void inject(ArticlesFragment articlesFragment);
}
