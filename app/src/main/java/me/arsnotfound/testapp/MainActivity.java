package me.arsnotfound.testapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import me.arsnotfound.testapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private final List<Player> players = new ArrayList<>();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PlayerAdapter adapter = new PlayerAdapter(players);
        binding.recyclerView.setAdapter(adapter);

        binding.addBtn.setOnClickListener(v -> {
            Player player = new Player(
                    binding.nameField.getText().toString(),
                    Integer.parseInt(binding.ballField.getText().toString())
            );

            players.add(player);

            binding.bestPlayerText.setText("Лучший игрок:" + players.get(players.size() - 1).getName());
            adapter.notifyItemInserted(players.size() - 1);
        });

        binding.selectBtn.setOnClickListener(v -> {
            StringBuilder result = new StringBuilder();
            List<String> resultList = new ArrayList<>();
            for (Player p: players) {
                if (p.isChecked())
                    resultList.add(p.getName());
            }

            for (int i = 0; i < resultList.size(); i++) {
                result.append("\n");
                result.append(resultList.get(i));
            }

            binding.selectedText.setText("Выбрано: " + result);
        });

    }
}
