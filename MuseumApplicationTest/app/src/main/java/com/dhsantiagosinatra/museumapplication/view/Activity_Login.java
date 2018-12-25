package com.dhsantiagosinatra.museumapplication.view;

import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

//Facebook Login(1/3):
import com.dhsantiagosinatra.museumapplication.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
//Facebook Login(1/3).
//Firebase Facebook Login (1/4):
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//Firebase Facebook Login (1/4).

public class Activity_Login extends AppCompatActivity {

    //Firebase Facebook Login(2/4):
    private FirebaseAuth mAuth;
    private AccessTokenTracker accessTokenTracker;
    //Firebase Facebook Login(2/4).

    //Facebook Login(2/3):
    private CallbackManager callbackManager;
    //Facebook Login(2/3).

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);

        //printHash();

        final Intent cambioLoginToMain = new Intent(Activity_Login.this, MainActivity.class); //Intent para el cambio de activity cuando este logueado el usuario.

    //Firebase Facebook Login(3/4):
        mAuth = FirebaseAuth.getInstance();
    //Firebase Facebook Login(3/4).

    //Facebook Login(3/3):
        callbackManager = CallbackManager.Factory.create(); //administrador de devolucion de llamadas.


        AccessToken accessToken = AccessToken.getCurrentAccessToken(); //comprobar estado del inicio de sesion.
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        if (isLoggedIn){ //accion que se realiza si el usuario ya esta logeado.
            startActivity(cambioLoginToMain);
            Toast.makeText(this, "Ya esta logueado", Toast.LENGTH_SHORT).show();
        }


        LoginButton loginButton = findViewById(R.id.login_button);

        loginButton.setReadPermissions("email");

    // Registro de llamada.
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
                startActivity(cambioLoginToMain);
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }

    // Devolucion de llamada.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    //Facebook Login (3/3).

    //Firebase Facebook Login (4/4):
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    //Método para loguear al usuario con Firebase
    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //Empieza a escuchar la sesión
                            accessTokenTracker.startTracking();
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Activity_Login.this, "Firebase Authentication success", Toast.LENGTH_SHORT).show();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Activity_Login.this, "Firebase Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                    }
                });
    }

    //Actualizo la UI en caso que el usuario esté logueado
    private void updateUI(FirebaseUser firebaseUser){
        if (firebaseUser!=null && AccessToken.getCurrentAccessToken()!=null) {
            //accessTokenTracker.startTracking();
        }
    }
    //Firebase Facebook Login (4/4).

    //Facebook HashKeyObtainer:

    /*

    private void printHash() {
        try {

            PackageInfo info =
                    getPackageManager().getPackageInfo(getApplicationContext().getPackageName(),
                            PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.v("MY KEY HASH:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    */

    //Facebook HashKeyObtainer.




}
