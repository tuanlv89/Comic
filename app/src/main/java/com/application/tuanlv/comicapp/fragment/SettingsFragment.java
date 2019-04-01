package com.application.tuanlv.comicapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tuanlv.comicapp.R;

public class SettingsFragment extends Fragment implements View.OnClickListener {
    RelativeLayout tvUserName, tvChangePass, tvLogout;
    RelativeLayout tvAboutUs, tvFeedback, tvRateApp;
    ImageView imgAvatar;
    Toolbar toolbar;
    public static SettingsFragment newInstance() {
        SettingsFragment settingsFragment = new SettingsFragment();
        return settingsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment, container, false);
        initView(view);

        tvUserName.setOnClickListener(this);
        tvChangePass.setOnClickListener(this);
        tvAboutUs.setOnClickListener(this);
        tvFeedback.setOnClickListener(this);
        tvRateApp.setOnClickListener(this);
        tvLogout.setOnClickListener(this);

        return view;
    }

    private void initView(View view) {
        tvRateApp = view.findViewById(R.id.tv_rate_app_setting);
        tvUserName = view.findViewById(R.id.tv_change_username);
        tvChangePass = view.findViewById(R.id.tv_change_passwd);
        tvAboutUs = view.findViewById(R.id.tv_about_us_setting);
        tvFeedback = view.findViewById(R.id.tv_feed_back_setting);
        tvLogout = view.findViewById(R.id.tv_logout_setting);
        imgAvatar = view.findViewById(R.id.img_avatar_setting);
        toolbar = view.findViewById(R.id.toolbar_settings);
        ((AppCompatActivity)getActivity()).getSupportActionBar();
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getContext(), "Compoinent is clicked", Toast.LENGTH_SHORT).show();
    }
}
