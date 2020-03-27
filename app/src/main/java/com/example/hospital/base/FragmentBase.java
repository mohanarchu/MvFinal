package com.example.hospital.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class FragmentBase extends Fragment {
    private Unbinder unbinder;
    AlertDialog dialog;
    ProgressDialog progressDialog;
    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layoutRes(), container, false);
        unbinder = ButterKnife.bind(this, view);
        onViewBound(view);
        return view;
    }

    @Override
    public void onDestroyView() {

        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    public boolean onBackPressed() {
        return false;
    }
    protected void onViewBound(View view) {

    }

    protected void showDialogue(){

        progressDialog = new ProgressDialog(getActivity());

        progressDialog.setMessage("Please wait..!");
        progressDialog.show();
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setCancelable(false); // if you want user to wait for some process to finish,
//        builder.setView(R.layout.alery_dialogue_layout);
//        dialog  = builder.create();
//        dialog.show();
    }

    protected void dismissDialogue(){
        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }

    protected void backClicked() {
    }


    @LayoutRes
    protected abstract int layoutRes();


}