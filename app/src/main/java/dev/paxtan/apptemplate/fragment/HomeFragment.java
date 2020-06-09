package dev.paxtan.apptemplate.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import dev.paxtan.apptemplate.R;
import dev.paxtan.apptemplate.functions.GeneralFunctions;
import dev.paxtan.apptemplate.ui.ExtendedFragment;

public class HomeFragment extends Fragment implements ExtendedFragment {
    private boolean doubleBackToExitPressedOnce;

    /** Default constructor. **/
    public HomeFragment() {
        // Default constructor.
    }

    /** Initialize fragment. Nothing to see here. **/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requireActivity().setTitle(R.string.app_name);
    }

    /** Display the exit dialog when the 'Back' button is pressed. **/
    @Override
    public boolean onBackPressed() {
        // Press back to exit
        if (doubleBackToExitPressedOnce) {
            GeneralFunctions.exitApp(requireActivity());
        } else {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(requireContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 1500);
        }
        return true;
    }
}