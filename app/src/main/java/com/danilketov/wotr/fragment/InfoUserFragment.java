package com.danilketov.wotr.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.danilketov.wotr.App;
import com.danilketov.wotr.R;
import com.danilketov.wotr.activity.UserActivity;
import com.danilketov.wotr.entity.UserInfo;
import com.danilketov.wotr.network.HttpClient;
import com.danilketov.wotr.repository.DataRepository;
import com.danilketov.wotr.viewmodel.InfoUserViewModel;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InfoUserFragment extends Fragment {

    public static final String EXTRA_ACCOUNT_ID = "accountId";

    private Toolbar toolbar;

    private TextView nicknameTextView;
    private TextView globalRatingTextView;
    private TextView lastBattleTimeTextView;
    private TextView maxXpTextView;
    private TextView battlesTextView;
    private TextView avgDamageAssistedTextView;
    private TextView avgDamageBlockedTextView;
    private TextView battleAvgXpTextView;
    private TextView hitsPercentsTextView;
    private TextView maxDamageTextView;
    private TextView survivedBattlesTextView;
    private TextView winsTextView;
    private TextView winsPercentsTextView;
    private TextView lossesTextView;
    private TextView drawsTextView;
    private TextView shotsTextView;
    private TextView fragsTextView;
    private TextView hitsTextView;
    private TextView spottedTextView;
    private TextView capturePointsTextView;
    private TextView damageDealtTextView;
    private TextView damageReceivedTextView;
    private TextView directHitsReceivedTextView;
    private TextView droppedCapturePointsTextView;
    private TextView xpTextView;
    private TextView avgDamageTextView;
    private TextView avgDamageAssistedRadioTextView;
    private TextView avgDamageAssistedTrackTextView;

    private HttpClient httpClient;
    private DataRepository dataRepository;
    private UserInfo userInfo;

    private InfoUserViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_user, container, false);

        setupToolbar(view);

        initView(view);

        httpClient = new HttpClient();
        dataRepository = App.getDataRepository();

        initViewModel();

        return view;
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(InfoUserViewModel.class);
        viewModel.getRepository().observe(this, (userInfo -> {
            if (userInfo != null) {
                displayUserInfo(userInfo);
            }
        }));

        viewModel.isException().observe(this, (isException) -> {
            if(isException != null && isException) {
                Toast.makeText(getActivity(), "Ошибка подключения", Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.isLoading().observe(this, (isLoading) -> {
            if(isLoading != null) {

            }
        });
    }

    private void setupToolbar(View view) {
        toolbar = view.findViewById(R.id.toolbar_info_user);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Статистика игрока");

        //Back button
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initView(View view) {
        nicknameTextView = view.findViewById(R.id.nickname_count_text_view);
        globalRatingTextView = view.findViewById(R.id.global_rating_count_text_view);
        lastBattleTimeTextView = view.findViewById(R.id.last_battle_time_count_text_view);
        maxXpTextView = view.findViewById(R.id.max_xp_count_text_view);
        battlesTextView = view.findViewById(R.id.battles_count_text_view);
        battleAvgXpTextView = view.findViewById(R.id.battle_avg_xp_count_text_view);
        hitsPercentsTextView = view.findViewById(R.id.hits_percents_count_text_view);
        maxDamageTextView = view.findViewById(R.id.max_damage_count_text_view);
        avgDamageTextView = view.findViewById(R.id.avg_damage_count_text_view);
        avgDamageAssistedTextView = view.findViewById(R.id.avg_damage_assisted_count_text_view);
        avgDamageBlockedTextView = view.findViewById(R.id.avg_damage_blocked_count_text_view);
        survivedBattlesTextView = view.findViewById(R.id.survived_battles_count_text_view);
        winsTextView = view.findViewById(R.id.wins_count_text_view);
        winsPercentsTextView = view.findViewById(R.id.wins_percents_count_text_view);
        lossesTextView = view.findViewById(R.id.losses_count_text_view);
        drawsTextView = view.findViewById(R.id.draws_count_text_view);
        shotsTextView = view.findViewById(R.id.shots_count_text_view);
        fragsTextView = view.findViewById(R.id.frags_count_text_view);
        hitsTextView = view.findViewById(R.id.hits_count_text_view);
        spottedTextView = view.findViewById(R.id.spotted_count_text_view);
        capturePointsTextView = view.findViewById(R.id.capture_points_count_text_view);
        damageDealtTextView = view.findViewById(R.id.damage_dealt_count_text_view);
        damageReceivedTextView = view.findViewById(R.id.damage_received_count_text_view);
        directHitsReceivedTextView = view.findViewById(R.id.direct_hits_received_count_text_view);
        droppedCapturePointsTextView = view.findViewById(R.id.dropped_capture_points_count_text_view);
        xpTextView = view.findViewById(R.id.xp_count_text_view);
        avgDamageAssistedRadioTextView = view.findViewById(R.id.avg_damage_assisted_radio_count_text_view);
        avgDamageAssistedTrackTextView = view.findViewById(R.id.avg_damage_assisted_track_count_text_view);

    }

    private void displayUserInfo(UserInfo userInfo) {
        String nickname = userInfo.getNickname();
        nicknameTextView.setText(nickname);

        String globalRating = String.valueOf(userInfo.getGlobalRating());
        globalRatingTextView.setText(globalRating);

        String lastBattleTime = String.valueOf(userInfo.getLastBattleTime());
        lastBattleTimeTextView.setText(getFormattedDate(lastBattleTime));

        String maxXp = String.valueOf(userInfo.getStatistics().getAll().getMaxXp());
        maxXpTextView.setText(getSpaceValue(maxXp));

        String battles = String.valueOf(userInfo.getStatistics().getAll().getBattles());
        battlesTextView.setText(battles);

        String avgDamageAssisted = String.valueOf(userInfo.getStatistics().getAll().getAvgDamageAssisted());
        avgDamageAssistedTextView.setText(avgDamageAssisted);

        String avgDamageBlocked = String.valueOf(userInfo.getStatistics().getAll().getAvgDamageBlocked());
        avgDamageBlockedTextView.setText(avgDamageBlocked);

        String battleAvgXp = String.valueOf(userInfo.getStatistics().getAll().getBattleAvgXp());
        battleAvgXpTextView.setText(getSpaceValue(battleAvgXp));

        int hitsPercents = userInfo.getStatistics().getAll().getHitsPercents();
        hitsPercentsTextView.setText(hitsPercents + "%");

        String maxDamage = String.valueOf(userInfo.getStatistics().getAll().getMaxDamage());
        maxDamageTextView.setText(getSpaceValue(maxDamage));

        String survivedBattles = String.valueOf(userInfo.getStatistics().getAll().getSurvivedBattles());
        survivedBattlesTextView.setText(getSpaceValue(survivedBattles));

        String wins = String.valueOf(userInfo.getStatistics().getAll().getWins());
        winsTextView.setText(getSpaceValue(wins));

        // Вычисление процента побед
        double battlesP = Integer.parseInt(battles);
        double winsP = Integer.parseInt(wins);
        if (battlesP == 0) {
            winsPercentsTextView.setText("0%");
        } else {
            double winsPercents = winsP*100/battlesP;
            // Форматирование числа (знаки после запятой)
            String formattedWinsPercents = new DecimalFormat("#0.00").format(winsPercents);
            winsPercentsTextView.setText(formattedWinsPercents + "%");
        }

        String losses = String.valueOf(userInfo.getStatistics().getAll().getLosses());
        lossesTextView.setText(getSpaceValue(losses));

        String draws = String.valueOf(userInfo.getStatistics().getAll().getDraws());
        drawsTextView.setText(getSpaceValue(draws));

        String shots = String.valueOf(userInfo.getStatistics().getAll().getShots());
        shotsTextView.setText(getSpaceValue(shots));

        String frags = String.valueOf(userInfo.getStatistics().getAll().getFrags());
        fragsTextView.setText(getSpaceValue(frags));

        String hits = String.valueOf(userInfo.getStatistics().getAll().getHits());
        hitsTextView.setText(getSpaceValue(hits));

        String spotted = String.valueOf(userInfo.getStatistics().getAll().getSpotted());
        spottedTextView.setText(getSpaceValue(spotted));

        String capturePoints = String.valueOf(userInfo.getStatistics().getAll().getCapturePoints());
        capturePointsTextView.setText(getSpaceValue(capturePoints));

        String damageDealt = String.valueOf(userInfo.getStatistics().getAll().getDamageDealt());
        damageDealtTextView.setText(getSpaceValue(damageDealt));

        // Вычисление среднего урона
        int battlesA = Integer.parseInt(battles);
        int damageDealtA = Integer.parseInt(damageDealt);
        if (battlesA == 0) {
            avgDamageTextView.setText("0");
        } else {
            int avgDamage = damageDealtA/battlesA;
            String abc = String.valueOf(avgDamage);
            avgDamageTextView.setText(abc);
        }

        String damageReceived = String.valueOf(userInfo.getStatistics().getAll().getDamageReceived());
        damageReceivedTextView.setText(getSpaceValue(damageReceived));

        String directHitsReceived = String.valueOf(userInfo.getStatistics().getAll().getDirectHitsReceived());
        directHitsReceivedTextView.setText(getSpaceValue(directHitsReceived));

        String droppedCapturePoints = String.valueOf(userInfo.getStatistics().getAll().getDroppedCapturePoints());
        droppedCapturePointsTextView.setText(getSpaceValue(droppedCapturePoints));

        String xp = String.valueOf(userInfo.getStatistics().getAll().getXp());
        xpTextView.setText(getSpaceValue(xp));

        String avgDamageAssistedRadio = String.valueOf(userInfo.getStatistics().getAll().getAvgDamageAssistedRadio());
        avgDamageAssistedRadioTextView.setText(avgDamageAssistedRadio);

        String avgDamageAssistedTrack = String.valueOf(userInfo.getStatistics().getAll().getAvgDamageAssistedTrack());
        avgDamageAssistedTrackTextView.setText(avgDamageAssistedTrack);
    }

    @NotNull
    private String getSpaceValue(String numeberInt) {
        int value = Integer.parseInt(numeberInt);
        return String.format("%,d", value);
    }

    private String getFormattedDate(String dateString) {
        Date date = new Date(Long.valueOf(dateString) * 1000);
        SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return formatDate.format(date);
    }

    public void updateData(String accountId) {
        viewModel.updateData(accountId);
    }
}
