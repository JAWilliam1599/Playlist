package com.example.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements simpleFragment.SimpleFragmentListener {
    private boolean favorite = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        App();
    }

    private void App() {
        ArrayList<Songs> Songs_list = get_Play_List_From_File();
        Adapter_Recycleview itemAdapter = new Adapter_Recycleview(Songs_list);

        RecyclerView recyclerView = findViewById(R.id.Playlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(itemAdapter);

        itemAdapter.setOnClickListener(new Adapter_Recycleview.OnClickListener() {
            @Override
            public void onClick(int position, Songs songs, Adapter_Recycleview.ViewHolder holder) {
                Intent openintent = new Intent(MainActivity.this, DetailActivity.class);
                openintent.putExtra("Details_name", songs.getName());
                openintent.putExtra("Details_des", songs.getDescription());
                startActivity(openintent);
            }
        });
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean("Favorite", favorite);
        super.onSaveInstanceState(savedInstanceState);
    }
    private ArrayList<Songs> get_Play_List_From_File() {
        //Futher feature: Using Play_list.txt to import data
        ArrayList<Songs> Play_list = new ArrayList<>();
        Play_list.add(new Songs("1. Thấy chưa",
                "Hát cho giờ ra chơi, mà trời mưa rơi những ngày em không thấy nắng\n" +
                        "Em thấy không có bạn, em đứt ngang dây đàn, em sẽ không ghi bàn hôm nay\n" +
                        "Cứ ngồi đây thôi, cùng lời anh nói, cứ để cơn giông đó tới\n" +
                        "Cho tới hôm anh về, giữ lấy cánh thư để trong tay\n\n" +
                        "Bởi vì sẽ có ngày em thấy\n" +
                        "Những chuyện buồn hôm ấy\n" +
                        "Chỉ là chuyện cười hôm nay\n" +
                        "Dù nói không buồn cười mấy\n" +
                        "Nhưng cười để khi em vui trở lại\n" +
                        "Anh sẽ nói khẽ bên tai\n" +
                        "Thấy chưa, thấy chưa em\n\n" +
                        "Cho dù ta ưa, một ngày không mưa, nhưng làm sao cây sống mãi?\n" +
                        "Nếu nắng chang chang hoài, không có hôm mưa lại, như cái hôm em phải chào thua\n\n" +
                        "Buông hai tay, buông hai tay\n" +
                        "Em đã cố, đã cố hôm nay\n" +
                        "Giờ kệ nó đi, em không làm gì\n" +
                        "Chỉ nhìn mặt trời đi qua\n\n" +
                        "Sẽ có ngày em thấy\n" +
                        "Những chuyện buồn hôm ấy\n" +
                        "Chỉ là chuyện cười hôm nay\n" +
                        "Dù nói không buồn cười mấy\n" +
                        "Nhưng cười để khi em vui trở lại\n" +
                        "Anh sẽ nói khẽ bên tai\n" +
                        "Thấy chưa, thấy chưa em\n" +
                        "Thấy chưa, thấy chưa em\n\n" +
                        "Nắng bắt đầu lên đó\n" +
                        "Mưa bắt đầu tan đó\n" +
                        "Cho dù chuyện đời vẫn khó\n" +
                        "Níu lấy những gì em có\n" +
                        "Để chờ ngày mai anh quay trở lại\n" +
                        "Anh sẽ nói khẽ bên tai\n" +
                        "Thấy chưa, thấy chưa em\n\n" +
                        "Ngày mai có nắng vàng sẽ tàn nhanh\n" +
                        "Mình đâu có dễ dàng giữ lại tuổi xanh\n" +
                        "Thế nên đừng chờ may mắn\n" +
                        "Đừng ôm cay đắng mãi\n\n" +
                        "Sẽ có ngày em thấy\n" +
                        "Những chuyện buồn hôm ấy\n" +
                        "Chỉ là chuyện cười hôm nay\n" +
                        "Dù nói không buồn cười mấy\n" +
                        "Nhưng cười để khi em vui trở lại\n" +
                        "Anh sẽ nói khẽ bên tai\n" +
                        "Thấy chưa, thấy chưa em\n\n" +
                        "Có sao, có sao đâu\n" +
                        "Told you, told you so"));
        Play_list.add(new Songs("2. Xin lỗi", "Trưởng thành hơn\n" +
                "Khoẻ mạnh hơn\n" +
                "Trải lại ơn người cưu mang chính mình\n" +
                "Mình không cố định\n" +
                "Một nơi bất kì\n" +
                "Nên anh rời đi\n" +
                "Điều tất nhiên\n" +
                "\n" +
                "Ừ anh sai, thì anh sai\n" +
                "Nhưng mà anh sai vì anh là chính mình\n" +
                "Nhiều khi thất tình,\n" +
                "để không mất mình\n" +
                "Thế nhưng nhiều khi...\n" +
                "\n" +
                "Nhiều khi anh ước\n" +
                "mình xin lỗi trước\n" +
                "để bình an lướt qua thời gian\n" +
                "Để mình không mắt mờ\n" +
                "dù anh vẫn sợ phải mất em,\n" +
                "mà chưa biết em\n" +
                "\n" +
                "Mà anh không chắc,\n" +
                "mình làm được gì khác\n" +
                "Dù sao anh vẫn chỉ là anh\n" +
                "Một dòng sông hối hận\n" +
                "trào tuông bất tận,\n" +
                "để mất nhau rồi xin lỗi sau..."));
        return Play_list;
    }

    @Override
    public void onFavoriteStateChanged(boolean isFavorite) {
        favorite = isFavorite;
    }
}