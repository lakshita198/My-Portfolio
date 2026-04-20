package com.example.myportfolio;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    Button btnPlay;
    VideoView videoView;

    LinearLayout educationHeader;
    LinearLayout educationExpandableContent;
    ImageView educationArrow;

    LinearLayout skillsHeader;
    LinearLayout skillsExpandableContent;
    ImageView skillsArrow;

    LinearLayout projectsHeader;
    LinearLayout projectsExpandableContent;
    ImageView projectsArrow;

    Button btnContact;
    Button btnLinkedIn;
    Button btnResume;

    @SuppressLint({"ClickableViewAccessibility", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.btnPlay);
        videoView = findViewById(R.id.videoView);

        // Education Section
        educationHeader = findViewById(R.id.educationHeader);
        educationExpandableContent = findViewById(R.id.educationExpandableContent);
        educationArrow = findViewById(R.id.educationArrow);

        educationHeader.setOnClickListener(v -> {
            if (educationExpandableContent.getVisibility() == View.GONE) {
                educationExpandableContent.setVisibility(View.VISIBLE);
                educationArrow.setImageResource(android.R.drawable.arrow_up_float);
            } else {
                educationExpandableContent.setVisibility(View.GONE);
                educationArrow.setImageResource(android.R.drawable.arrow_down_float);
            }
        });

        // Skills Section
        skillsHeader = findViewById(R.id.skillsHeader);
        skillsExpandableContent = findViewById(R.id.skillsExpandableContent);
        skillsArrow = findViewById(R.id.skillsArrow);

        skillsHeader.setOnClickListener(v -> {
            if (skillsExpandableContent.getVisibility() == View.GONE) {
                skillsExpandableContent.setVisibility(View.VISIBLE);
                skillsArrow.setImageResource(android.R.drawable.arrow_up_float);
            } else {
                skillsExpandableContent.setVisibility(View.GONE);
                skillsArrow.setImageResource(android.R.drawable.arrow_down_float);
            }
        });

        // Projects Section
        projectsHeader = findViewById(R.id.projectsHeader);
        projectsExpandableContent = findViewById(R.id.projectsExpandableContent);
        projectsArrow = findViewById(R.id.projectsArrow);

        projectsHeader.setOnClickListener(v -> {
            if (projectsExpandableContent.getVisibility() == View.GONE) {
                projectsExpandableContent.setVisibility(View.VISIBLE);
                projectsArrow.setImageResource(android.R.drawable.arrow_up_float);
            } else {
                projectsExpandableContent.setVisibility(View.GONE);
                projectsArrow.setImageResource(android.R.drawable.arrow_down_float);
            }
        });

        // Contact Me Button
        btnContact = findViewById(R.id.btnContact);
        btnContact.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:2023pietcslakshita097@poornima.org"));
            startActivity(intent);
        });

        // LinkedIn Button
        btnLinkedIn = findViewById(R.id.btnLinkedIn);
        btnLinkedIn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/lakshita-rakhecha-063ab5318/"));
            startActivity(intent);
        });

        // Resume Button
        btnResume = findViewById(R.id.btnResume);
        btnResume.setOnClickListener(v -> {
            try {
                InputStream inputStream = getResources().openRawResource(R.raw.resume);
                File file = new File(getCacheDir(), "resume.pdf");
                FileOutputStream fileOutputStream = new FileOutputStream(file);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, length);
                }

                fileOutputStream.close();
                inputStream.close();

                Uri path = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", file);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(path, "application/pdf");
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(intent);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Play Video Button
        btnPlay.setOnClickListener(v -> {
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.myvideo);
            videoView.setVideoURI(uri);
            videoView.start();
        });

        // Pass touch events from VideoView to the parent (ScrollView)
        videoView.setOnTouchListener((v, event) -> false);
    }
}
