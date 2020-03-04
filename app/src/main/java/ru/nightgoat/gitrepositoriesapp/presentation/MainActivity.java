package ru.nightgoat.gitrepositoriesapp.presentation;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.List;

import ru.nightgoat.gitrepositoriesapp.R;
import ru.nightgoat.gitrepositoriesapp.data.api.RepModel;


public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter presenter;
    private EditText editText;
    private MaterialButton getBtn;
    private ImageView avatarIV;
    private TextView nicknameTV;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MainRVadapter mAdapter;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initRecycler();
        getBtn.setOnClickListener((v) -> {
            if ((user = editText.getText().toString()).isEmpty()) {
                Toast.makeText(this, "Please enter nickname", Toast.LENGTH_SHORT).show();
            } else {
                if (isNetworkConnected()) presenter.getRepos(user);
                else {
                    Toast.makeText(this, "No internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initViews() {
        editText = findViewById(R.id.editText);
        getBtn = findViewById(R.id.getBtn);
        recyclerView = findViewById(R.id.recyclerView);
        avatarIV = findViewById(R.id.avatarIV);
        nicknameTV = findViewById(R.id.nicknameTV);
        progressBar = findViewById(R.id.progressBar);
    }

    private void initRecycler() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mAdapter = new MainRVadapter();
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void setAdapter(List<RepModel> repos) {
        mAdapter.updateData(repos);
    }

    @Override
    public void setUser(RepModel.Owner owner) {
        if (owner.avatarUrl != null) Picasso.get().load(owner.avatarUrl).into(avatarIV);
        nicknameTV.setText(owner.login);
    }

    @Override
    public void startProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @SuppressWarnings("deprecation")
    public boolean isNetworkConnected() {
        final ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            if (Build.VERSION.SDK_INT < 23) {
                final NetworkInfo ni = cm.getActiveNetworkInfo();

                if (ni != null) {
                    return (ni.isConnected() && (ni.getType() == ConnectivityManager.TYPE_WIFI || ni.getType() == ConnectivityManager.TYPE_MOBILE));
                }
            } else {
                final Network n = cm.getActiveNetwork();

                if (n != null) {
                    final NetworkCapabilities nc = cm.getNetworkCapabilities(n);

                    if (nc != null) {
                        return (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
                    }
                }
            }
        }
        return false;
    }
}
