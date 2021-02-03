package ru.shamilprog.android.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class GameActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return GameFragment.newInstance();
    }
}