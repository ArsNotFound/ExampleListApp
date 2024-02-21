package me.arsnotfound.testapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import me.arsnotfound.testapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private final List<Player> players = new ArrayList<>();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        PlayerAdapter adapter = new PlayerAdapter(this, players);
        binding.listView.setAdapter(adapter);
        binding.listView.setOnItemClickListener((parent, view, position, id) -> {
            adapter.toggleChecked(position);
        });

        binding.addBtn.setOnClickListener(v -> {
            Player player = new Player(
                    binding.nameField.getText().toString(),
                    Integer.parseInt(binding.ballField.getText().toString())
            );

            players.add(player);
            players.sort(new Player.CompBall());

            binding.bestPlayerText.setText("Лучший игрок:" + players.get(players.size() - 1).getName());
            adapter.notifyDataSetChanged();
        });

        binding.selectBtn.setOnClickListener(v -> {
            StringBuilder result = new StringBuilder();
            List<String> resultList = adapter.getCheckedItems();

            for (int i = 0; i < resultList.size(); i++) {
                result.append("\n");
                result.append(resultList.get(i));
            }

            binding.selectedText.setText("Выбрано: " + result);
            Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
        });

    }
}
