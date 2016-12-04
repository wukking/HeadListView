package com.wuys.wuyson.headandlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ParallaxListView plistView;
    private String[] indexArr = {"A","B","C","D","E","F","G","H","I","J",
            "K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plistView = (ParallaxListView) findViewById(R.id.pListView);
        plistView.setOverScrollMode(ListView.OVER_SCROLL_NEVER);

        View headerView = View.inflate(this,R.layout.layout_head,null);
        ImageView imageView = (ImageView) headerView.findViewById(R.id.imageView);
        plistView.setParallsxImageView(imageView);
        plistView.addHeaderView(headerView);

        plistView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,indexArr));

    }
}
