package com.example.myemployee;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.InetAddresses;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ProfileActivity extends AppCompatActivity {

    ImageView uploadImage;
    Button saveButton;
    EditText uploadName , uploadAge, uploadCity , uploadSkill , uploadEducation , uploadSalary , uploadType , uploadEmail , uploadCV , uploadPhone;
    String imageURL;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        uploadImage = findViewById(R.id.uploadImage);
        uploadAge = findViewById(R.id.uploadAge);
        uploadName = findViewById(R.id.uploadName);
        uploadCity = findViewById(R.id.uploadCity);
        uploadSkill = findViewById(R.id.uploadSkill);
        uploadEducation = findViewById(R.id.uploadEducation);
        uploadSalary = findViewById(R.id.uploadSalary);
        uploadType = findViewById(R.id.uploadType);
        saveButton = findViewById(R.id.saveButton);
        uploadPhone = findViewById(R.id.uploadphone);
        uploadEmail = findViewById(R.id.uploadmail);
        uploadCV = findViewById(R.id.uploadCV);

        uploadPhone.setAutoLinkMask(Linkify.ALL);
        uploadCV.setAutoLinkMask(Linkify.ALL);
        uploadEmail.setAutoLinkMask(Linkify.ALL);





        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            uploadImage.setImageURI(uri);
                        }  else{
                            Toast.makeText(ProfileActivity.this , "No Image Selected" , Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    public void saveData(){


        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Android Images")
                .child(uri.getLastPathSegment());

        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageURL = urlImage.toString();
                uploadData();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();

            }
        });
    }
    public void uploadData(){
        String name = uploadName.getText().toString();
        String age = uploadAge.getText().toString();
        String city = uploadCity.getText().toString();
        String education = uploadEducation.getText().toString();
        String skill = uploadSkill.getText().toString();
        String type = uploadType.getText().toString();
        String salary = uploadSalary.getText().toString();
        String email = uploadEmail.getText().toString();
        String cv = uploadCV.getText().toString();
        String phone = uploadPhone.getText().toString();

        DataClass dataClass = new DataClass(name , age , city , education , skill , type , salary , imageURL , email , cv , phone);

        FirebaseDatabase.getInstance().getReference("MyEmployee").child(name)
                .setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ProfileActivity.this , "Saved" , Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ProfileActivity.this , e.getMessage().toString() , Toast.LENGTH_SHORT).show();
                        finish();

                    }
                });


    }
}