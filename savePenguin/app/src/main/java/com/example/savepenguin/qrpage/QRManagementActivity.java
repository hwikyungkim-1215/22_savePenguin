package com.example.savepenguin.qrpage;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.savepenguin.IpSetting;
import com.example.savepenguin.R;
import com.example.savepenguin.account.SharedPreference;
import com.example.savepenguin.mainpage.MainActivity;
import com.example.savepenguin.model.QR;
import com.example.savepenguin.mypage.PenguinShopActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class QRManagementActivity extends AppCompatActivity {

    RecyclerView userList;
    LinearLayoutManager linearLayoutManager;
    public UserListAdapter adapter;
    public static Context context;
    public ArrayList<QR> items = new ArrayList<>();
    private String userid;
    IpSetting ipSetting = new IpSetting();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_management);
        userid = SharedPreference.getAttribute(getApplicationContext(), "userid");
        context = this;
        Log.v("QR 관리 페이지", "QR 관리 Activity 시작");

        try {
            getQRList task = new getQRList();
            String result = task.execute(userid).get();
            Log.v("펭귄샵 페이지", "통신 리턴값 : " + result);
        } catch (Exception e) {

        }

        Button createQrBtn = findViewById(R.id.button_createQR);
        createQrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("QR 관리 페이지", "QR 발급 버튼 누름");

                Intent intent = new Intent(getApplicationContext(), CreateQRActivity.class);
                startActivity(intent);
            }
        });


        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sample_qr);

        userList = findViewById(R.id.ListView_QRList);
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        adapter = new UserListAdapter(items, userid);
        userList.setLayoutManager(linearLayoutManager);
        userList.setAdapter(adapter);
    }

    public void showQR(String qrname) {

        //인텐트 선언 및 정의
        Intent intent = new Intent(this, ShowQRActivity.class);

        //입력한 input값을 intent로 전달한다.
        intent.putExtra("qrname", qrname);
        //액티비티 이동
        startActivity(intent);
    }

    class getQRList extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        String id;

        @Override
        // doInBackground의 매개변수 값이 여러개일 경우를 위해 배열로
        protected String doInBackground(String... strings) {
            try {
                id = strings[0];
                String str;
                URL url = new URL(ipSetting.getBaseUrl() + "/qrcode/" + strings[0] );  // 어떤 서버에 요청할지(localhost 안됨.)
                // ex) http://123.456.789.10:8080/hello/android
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");                              //데이터를 POST 방식으로 전송합니다.
                conn.setDoOutput(true);
                conn.setConnectTimeout(1000);

                // 서버에 보낼 값 포함해 요청함.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "userid=" + strings[0]; // GET방식으로 작성해 POST로 보냄 ex) "id=admin&pwd=1234";
                osw.write(sendMsg);                           // OutputStreamWriter에 담아 전송
                osw.flush();

                // jsp와 통신이 잘 되고, 서버에서 보낸 값 받음.
                if (conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                } else {    // 통신이 실패한 이유를 찍기위한 로그
                    Log.i("통신 결과", conn.getResponseCode() + "에러");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 서버에서 보낸 값을 리턴합니다.
            return receiveMsg;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (s != null) {
                JSONObject jsonObj = null;
                JSONArray qrList = null;
                try {
                    jsonObj = new JSONObject(s);
                    qrList = jsonObj.getJSONArray("qrlist");
                    System.out.println("길이 " +qrList.length());
                    for (int i = 0; i < qrList.length(); i++) {
                        JSONObject qr = qrList.getJSONObject(i);
                        String qrname = qr.getString("qrname");
                        String imageData = qr.getString("data");
                        System.out.println("qrname = " + qrname);
                        System.out.println("imageData = " + imageData);
                        //byte[] image = imageData.getBytes(StandardCharsets.UTF_8);
                        byte[] image = Base64.decode(imageData, Base64.DEFAULT);

                        //byte[] image = imageData.getBytes();
                        Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);

                        //items.add(new QR(qrname, "test", bmp));
                        add(new QR(qrname, "test", bmp));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
            }

            System.out.println(s);
        }
    }
    public void add(QR qr) {
        boolean isValid = true;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getQrName().equals(qr.getQrName())) {
                isValid = false;
                break;
            }
        }

        if (isValid) {
            items.add(qr);
        }
    }
    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        String id;

        @Override
        // doInBackground의 매개변수 값이 여러개일 경우를 위해 배열로
        protected String doInBackground(String... strings) {
            try {
                id = strings[0];
                String str;
                URL url = new URL(ipSetting.getBaseUrl() + "/TestUpdateQR");  // 어떤 서버에 요청할지(localhost 안됨.)
                // ex) http://123.456.789.10:8080/hello/android
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");                              //데이터를 POST 방식으로 전송합니다.
                conn.setDoOutput(true);
                conn.setConnectTimeout(1000);

                // 서버에 보낼 값 포함해 요청함.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "userid=" + strings[0]; // GET방식으로 작성해 POST로 보냄 ex) "id=admin&pwd=1234";
                osw.write(sendMsg);                           // OutputStreamWriter에 담아 전송
                osw.flush();

                // jsp와 통신이 잘 되고, 서버에서 보낸 값 받음.
                if (conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                } else {    // 통신이 실패한 이유를 찍기위한 로그
                    Log.i("통신 결과", conn.getResponseCode() + "에러");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 서버에서 보낸 값을 리턴합니다.
            return receiveMsg;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.

            System.out.println(s);
        }
    }

}


class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.Holder> {

    ArrayList<QR> items = new ArrayList<>();
    private String userid;

    public UserListAdapter(ArrayList<QR> items, String userid) {
        this.items = items;
        this.userid = userid;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_qritem, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        QR item = items.get(position);
        holder.profileImg.setImageBitmap(item.getProfile());
        holder.qrName.setText(item.getQrName());
        holder.about.setText(item.getAbout());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView profileImg;
        private EditText qrName, about;
        private Button qrBtn, qrEditBtn;


        public Holder(@NonNull View itemView) {
            super(itemView);
            profileImg = itemView.findViewById(R.id.penguin_Item_img);
            qrName = itemView.findViewById(R.id.edittext_qrname);
            about = itemView.findViewById(R.id.edittext_aboutqr);
            qrBtn = itemView.findViewById(R.id.button_qrBtn);
            qrEditBtn = itemView.findViewById(R.id.button_buyItem);

            qrName.setTag(qrName.getKeyListener());
            qrName.setKeyListener(null);
            about.setTag(about.getKeyListener());
            about.setKeyListener(null);

            // 아이템 클릭시
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    // 아이템이 있는지 없는지 체크
                    if (pos != RecyclerView.NO_POSITION) {
                        QR qr = items.get(pos);
                        Log.v("QR 관리 페이지", pos + "번째 QR 누름");
                    }
                }
            });

            //qr 버튼 누름 => qr 보여주는 페이지로 넘어가야함
            qrBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Log.v("QR 관리 페이지", pos + "번째 QR 보기 버튼 누름");
                        ((QRManagementActivity) QRManagementActivity.context).showQR(items.get(pos).getQrName());

                    }
                }
            });

            //qr 내용 수정 내용
            qrEditBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Log.v("QR 관리 페이지", pos + "번째 QR 수정 버튼 누름");

                        if (qrName.getKeyListener() == null) {
                            Log.v("QR 관리 페이지", pos + "번째 qr 정보 수정 모드");
                            qrName.setKeyListener((KeyListener) qrName.getTag());
                            about.setKeyListener((KeyListener) about.getTag());
                        } else {
                            Log.v("QR 관리 페이지", pos + "번째 qr 정보 업데이트");
                            qrName.setKeyListener(null);
                            about.setKeyListener(null);

                            //서버에 수정된 qr이름과 설명 업데이트 해야함
                            //선택된 qr 찾아서 정보 담아 보내기
                            ContentValues qrInfo = new ContentValues();
                            qrInfo.put("qrname", qrName.getText().toString());
                            qrInfo.put("about", about.getText().toString());
                            qrInfo.put("userid", userid);
                            //qrInfo.put("cuptype",);
                        }

                    }
                }
            });
        }
    }
}