package z9.msp.gob.mspfichafamiliar.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import z9.msp.gob.mspfichafamiliar.R;
import z9.msp.gob.mspfichafamiliar.fragment.ListFormFragment;

public class ListFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(getTitle());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ListFormFragment leadsFragment = (ListFormFragment)
                getSupportFragmentManager().findFragmentById(R.id.content_list_form);

        if (leadsFragment == null) {
            leadsFragment = ListFormFragment.newInstance("parm1","parm2");
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_list_form, leadsFragment)
                    .commit();
        }
    }

}
