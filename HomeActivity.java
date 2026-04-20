package com.example.myportfolio;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button btnPlay, btnLinkedIn, btnGitHub, btnResume, btnContact;
    VideoView videoView;
    ImageView arrowPersonal, arrowSkills, arrowEducation, arrowAchievements;
    LinearLayout expandablePersonal, expandableSkills, expandableEducation, expandableAchievements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnPlay = findViewById(R.id.btnPlay);
        videoView = findViewById(R.id.videoView);

        // Social and Resume Buttons
        btnLinkedIn = findViewById(R.id.btnLinkedIn);
        btnGitHub = findViewById(R.id.btnGitHub);
        btnResume = findViewById(R.id.btnResume);

        // Expandable Sections
        arrowPersonal = findViewById(R.id.arrowPersonal);
        arrowSkills = findViewById(R.id.arrowSkills);
        arrowEducation = findViewById(R.id.arrowEducation);
        arrowAchievements = findViewById(R.id.arrowAchievements);
        expandablePersonal = findViewById(R.id.expandablePersonal);
        expandableSkills = findViewById(R.id.expandableSkills);
        expandableEducation = findViewById(R.id.expandableEducation);
        expandableAchievements = findViewById(R.id.expandableAchievements);

        // Contact Button
        btnContact = findViewById(R.id.btnContact);

        // OnClick Listeners
        arrowPersonal.setOnClickListener(v -> toggleVisibility(expandablePersonal, arrowPersonal));
        arrowSkills.setOnClickListener(v -> toggleVisibility(expandableSkills, arrowSkills));
        arrowEducation.setOnClickListener(v -> toggleVisibility(expandableEducation, arrowEducation));
        arrowAchievements.setOnClickListener(v -> toggleVisibility(expandableAchievements, arrowAchievements));

        btnLinkedIn.setOnClickListener(v -> openUrl("https://www.linkedin.com/in/lakshita-rakhecha-6b962328a/"));
        btnGitHub.setOnClickListener(v -> openUrl("https://github.com/Lakshitaa22"));
        // TODO: Implement Resume Download

        btnContact.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:lakshitaraheja17@gmail.com"));
            startActivity(intent);
        });

        // Play Video Button
        btnPlay.setOnClickListener(v -> {
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.myvideo);
            videoView.setVideoURI(uri);

            MediaController mediaController = new MediaController(HomeActivity.this);
            videoView.setMediaController(mediaController);
            mediaController.setAnchorView(videoView);

            videoView.start();
        });
    }

    private void toggleVisibility(LinearLayout expandableLayout, ImageView arrow) {
        if (expandableLayout.getVisibility() == View.GONE) {
            expandableLayout.setVisibility(View.VISIBLE);
            arrow.setImageResource(android.R.drawable.arrow_up_float);
        } else {
            expandableLayout.setVisibility(View.GONE);
            arrow.setImageResource(android.R.drawable.arrow_down_float);
        }
    }

    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
