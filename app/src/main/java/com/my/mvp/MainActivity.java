package com.my.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.my.mvp.model.bean.UserBean;
import com.my.mvp.presenter.AccountPresenter;
import com.my.mvp.presenter.impl.AccountPresenterImpl;
import com.my.mvp.ui.activities.LoginActivity;
import com.my.mvp.ui.views.LoginView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements LoginView {

    @Bind(R.id.et_name)
    EditText mEtName;
    @Bind(R.id.et_pwd)
    EditText mEtPwd;
    @Bind(R.id.bt_commit)
    Button mBtCommit;
    private AccountPresenter mAccountPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mAccountPresenter = new AccountPresenterImpl(this);
    }

    @OnClick({R.id.bt_commit})
    public void login(View view) {
        switch (view.getId()) {
            case R.id.bt_commit:
                Intent intent = new Intent(this, LoginActivity.class);
                this.startActivity(intent);
                break;
        }
    }

    //返回
    @Override
    public void loginSuccess(UserBean userBean) {

    }

    @Override
    public void showError(String errorMsg) {

    }
}
