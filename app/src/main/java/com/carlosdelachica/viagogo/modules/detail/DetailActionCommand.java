package com.carlosdelachica.viagogo.modules.detail;

import android.app.Activity;
import android.content.Intent;

import com.carlosdelachica.viagogo.ui.ActionCommand;

public class DetailActionCommand implements ActionCommand {

    private Activity activity;
    private String alpha2Code;

    public DetailActionCommand(Activity activity, String alpha2Code) {
        this.activity = activity;
        this.alpha2Code = alpha2Code;
    }
    
    @Override public void execute() {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(DetailActivity.COUNTRY_ALPHA_2_CODE_EXTRA, alpha2Code);
        activity.startActivity(intent);
    }

}
