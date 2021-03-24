package com.example.myapplication.ui.login

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
        const val RC_SIGN_IN = 200
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.login_fragment, container, false)
        return mView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
        viewModel.user.observe(requireActivity(),{
            it?.let { firebaseUser ->
                Log.d("Test login result;",firebaseUser.email ?: "There are no mail")
            }
        })
        viewModel.login(requireActivity(),"jirayu@mail.com","12345678")
//        viewModel.register(requireActivity(),"jirayu@mail.com","12345678")

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        mView.findViewById<SignInButton>(R.id.sign_in_google_button).setOnClickListener {
            signIn()
        }
    }
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d("Test Google Auth", "firebaseAuthWithGoogle:" + account.id)
                account.idToken?.let {
                    viewModel.firebaseAuthWithGoogle(requireActivity(),it)
                }
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("Test Google Auth", "Google sign in failed", e)
            }
        }
    }

}