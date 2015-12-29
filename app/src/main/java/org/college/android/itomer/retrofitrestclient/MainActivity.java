package org.college.android.itomer.retrofitrestclient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import entities.GitContributor;
import entities.Item;
import entities.UserResults;
import rest.RestClient;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        Log.e("TomerBu", ConnectivityNotifier.isConnected(MainActivity.this) + "");
        initConnNotifier();

        gitHubClient();
    }

    private void initConnNotifier() {
        //Save the state of the connected and use the notifier to observe the internet state
        ConnectivityNotifier notifier = ConnectivityNotifier.getNotifier(this);
        notifier.addListener(new ConnectivityNotifier.ConnectivityListener() {
            @Override
            public void networkConnectivityStatusChanged(Context context, Intent intent) {
                Log.e("TomerBu", ConnectivityNotifier.isConnected(MainActivity.this) + "");
                //Disable all network calls and Update state.
            }
        });
    }

    @OnClick(R.id.fab)
    void fabClicked() {
        Snackbar.make(fab, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private void gitHubClient() {
        final ProgressDialog dialog = ProgressDialog.show(this, "Loading", "Fetching users from Github...");

        RestClient.GitAPI service = RestClient.getClient();

        service.getUsersNamed("TomerBu").enqueue(new Callback<UserResults>() {
            @Override
            public void onResponse(Response<UserResults> response, Retrofit retrofit) {
                List<Item> items = response.body().getItems();
                Log.e("Tomer", items.toString());
                dialog.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                dialog.dismiss();
            }
        });


        Call<List<GitContributor>> contributors = service.contributors("iAndroidCollege", "FragmentsDemo");
        contributors.enqueue(new Callback<List<GitContributor>>() {
            @Override
            public void onResponse(Response<List<GitContributor>> response, Retrofit retrofit) {
                System.out.println(response.body());
                List<GitContributor> items = response.body();
                for (GitContributor cont : items) {
                    Log.e("Tomer", cont.toString());
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                dialog.dismiss();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
