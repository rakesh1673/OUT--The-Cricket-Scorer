package com.example.out;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreUpdateActivity extends Activity implements View.OnClickListener {

    private Datasource datasource;

    private Button btnZero;
    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnFour;
    private Button btnFive;
    private Button btnSix;

    private TextView tvTeamOneName;
    private TextView tvTeamTwoName;

    private TextView tvTeamOneScore;
    private TextView tvTeamTwoScore;
    private TextView tvTeamOneOver;
    private TextView tvTeamOneRunRate;

    private Button btnOut;
    private Button btnEnd;
    private Button btnWd;
    private Button btnNb;
    private Button btnBye;
    private Button btnLegBye;

    private Button btnBowler;
    private Button btnBatsman;

    private TextView tvThisOver;

    private TextView tvStriker;
    private TextView tvStrikerScore;
    private TextView tvStrikerFour;
    private TextView tvStrikerSix;
    private TextView tvStrikerStrikeRate;

    private TextView tvNonStriker;
    private TextView tvNonStrikerScore;
    private TextView tvNonStrikerFour;
    private TextView tvNonStrikerSix;
    private TextView tvNonStrikerStrikeRate;

    private TextView tvBowlerName;
    private TextView tvBowlerOver;
    private TextView tvBowlerRun;
    private TextView tvBowlerWicket;
    private TextView tvBowlerEconomyRate;

    private TextView tvCommentary;

    private int strikerNo;
    private int nonStrikerNo;

    private boolean isTeamTwoInBatting;

    private StringBuilder thisOver;

    private int bowlerNo;

    private Team teamOne;
    private Team teamTwo;

    private int totalBall = 300;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_update_activity);

        datasource = new Datasource(this);

        btnZero = findViewById(R.id.zero_run_button);
        btnOne = findViewById(R.id.one_run_button);
        btnTwo = findViewById(R.id.two_run_button);
        btnThree = findViewById(R.id.three_run_button);
        btnFour = findViewById(R.id.four_run_button);
        btnFive = findViewById(R.id.five_run_button);
        btnSix = findViewById(R.id.six_run_button);

        btnBowler = findViewById(R.id.btn_bowler);
        btnBatsman = findViewById(R.id.btn_batsman);
        btnOut = findViewById(R.id.btn_out);
        btnEnd = findViewById(R.id.btnEnd);

        btnNb = findViewById(R.id.btn_nb);
        btnWd = findViewById(R.id.btn_wide);
        btnBye = findViewById(R.id.btn_bye);
        btnLegBye = findViewById(R.id.btn_leg_bye);

        btnNb.setOnClickListener(this);
        btnWd.setOnClickListener(this);
        btnBye.setOnClickListener(this);
        btnLegBye.setOnClickListener(this);


        tvTeamOneName = findViewById(R.id.team_one_name);
        tvTeamTwoName = findViewById(R.id.team_two_name);


        tvTeamOneScore = findViewById(R.id.team_one_score);
        tvTeamTwoScore = findViewById(R.id.team_two_score);
        tvTeamOneOver = findViewById(R.id.tv_over);
        tvTeamOneRunRate = findViewById(R.id.tv_runrate);

        tvStriker = findViewById(R.id.strikerName);
        tvStrikerScore = findViewById(R.id.strikerScore);
        tvStrikerFour = findViewById(R.id.strikerFour);
        tvStrikerSix = findViewById(R.id.strikerSix);
        tvStrikerStrikeRate = findViewById(R.id.strikerStrikeRate);

        tvNonStriker = findViewById(R.id.nonStrikerName);
        tvNonStrikerScore = findViewById(R.id.nonStrikerScore);
        tvNonStrikerFour = findViewById(R.id.nonStrikerFour);
        tvNonStrikerSix = findViewById(R.id.nonStrikerSix);
        tvNonStrikerStrikeRate = findViewById(R.id.nonStrikerStrikeRate);

        tvCommentary = findViewById(R.id.commentry);

        tvBowlerName = findViewById(R.id.bowlerName);
        tvBowlerOver = findViewById(R.id.bowlerOver);
        tvBowlerRun = findViewById(R.id.bowlerRun);
        tvBowlerWicket = findViewById(R.id.bowlerWicket);
        tvBowlerEconomyRate = findViewById(R.id.bowlerEconomyRate);

        tvThisOver = findViewById(R.id.thisOver);

        btnZero.setOnClickListener(this);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);

        btnBatsman.setOnClickListener(this);
        btnBowler.setOnClickListener(this);
        btnEnd.setOnClickListener(this);
        btnOut.setOnClickListener(this);

        tvTeamOneName.setOnClickListener(this);
        tvStriker.setOnClickListener(this);
        tvNonStriker.setOnClickListener(this);
        tvBowlerName.setOnClickListener(this);

        boolean continueFlag = getIntent().getBooleanExtra("CONTINUE", false);
        boolean startFlag = getIntent().getBooleanExtra("START", false);

        if (startFlag) {
            teamOne = new Team(1);
            teamTwo = new Team(2);
            int playerNum = getIntent().getIntExtra("PLAYERS", 11);
            for (int i = 0; i < playerNum; i++) {
                teamOne.players.add(new Player(i + 1));
                teamTwo.players.add(new Player(i + 1));
            }
            datasource.open();
            datasource.addTeamScore(teamOne);
            datasource.addTeamScore(teamTwo);
            datasource.insertPlayerOne(teamOne.players);
            datasource.insertPlayerTwo(teamTwo.players);
            datasource.close();
            totalBall = getIntent().getIntExtra("OVERS", 50) * 6;
        }

        if (continueFlag) {
            datasource.open();
            teamOne = datasource.getTeamScore(1);
            teamTwo = datasource.getTeamScore(2);
            teamOne.players = datasource.getPlayerOneList();
            teamTwo.players = datasource.getPlayerTwoList();
            datasource.close();

            totalBall = teamOne.ball;
        }

        strikerNo = 0;
        nonStrikerNo = 1;
        bowlerNo = 0;

        tvTeamOneName.setText(teamOne.name);
        tvTeamTwoName.setText(teamTwo.name);

        thisOver = new StringBuilder();
        tvCommentary.setText(teamOne.name + " won the toss & elected to bat first");

    }

    @Override
    protected void onPause() {
        super.onPause();
        datasource.open();

        teamOne.id = 1;
        teamTwo.id = 2;

        datasource.updateTeamScore(teamOne);
        datasource.updateTeamScore(teamTwo);
        datasource.updatePlayerOne(teamOne.players);
        datasource.updatePlayerTwo(teamTwo.players);

        datasource.close();
    }

    @Override
    public void onClick(View v) {
        if (v == btnEnd) {
            swapTeam();
            strikerNo = 0;
            nonStrikerNo = 1;
            bowlerNo = 0;
            isTeamTwoInBatting = true;
            tvTeamOneName.setText(teamOne.name);
            tvTeamTwoName.setText(teamTwo.name);
        } else if (v == btnZero) {
            teamOne.ball++;
            teamOne.players.get(strikerNo).ball++;
            teamTwo.players.get(bowlerNo).bowlerBall++;
            thisOver.append("0 ");
            if (teamOne.overBall() == 0) {
                Toast.makeText(this, "Over end. Change the Bowler", Toast.LENGTH_LONG).show();
            }
        } else if (v == btnOne) {
            teamOne.run++;
            teamOne.ball++;
            teamOne.players.get(strikerNo).run++;
            teamOne.players.get(strikerNo).ball++;
            teamTwo.players.get(bowlerNo).bowlerBall++;
            teamTwo.players.get(bowlerNo).bowlerRun++;
            thisOver.append("1 ");
            swapBatsman();
        } else if (v == btnTwo) {
            teamOne.run += 2;
            teamOne.ball++;
            teamOne.players.get(strikerNo).ball++;
            teamOne.players.get(strikerNo).run += 2;
            teamTwo.players.get(bowlerNo).bowlerBall++;
            teamTwo.players.get(bowlerNo).bowlerRun += 2;
            thisOver.append("2 ");
        } else if (v == btnThree) {
            teamOne.run += 3;
            teamOne.ball++;
            teamOne.players.get(strikerNo).ball++;
            teamOne.players.get(strikerNo).run += 3;
            teamTwo.players.get(bowlerNo).bowlerBall++;
            teamTwo.players.get(bowlerNo).bowlerRun += 3;
            thisOver.append("3 ");
            swapBatsman();
        } else if (v == btnFour) {
            teamOne.run += 4;
            teamOne.ball++;
            teamOne.players.get(strikerNo).ball++;
            teamOne.players.get(strikerNo).run += 4;
            teamOne.players.get(strikerNo).four++;
            teamTwo.players.get(bowlerNo).bowlerBall++;
            teamTwo.players.get(bowlerNo).bowlerRun += 4;
            thisOver.append("4 ");
        } else if (v == btnFive) {
            teamOne.run += 5;
            teamOne.ball++;
            teamOne.players.get(strikerNo).ball++;
            teamOne.players.get(strikerNo).run += 5;

            teamTwo.players.get(bowlerNo).bowlerBall++;
            teamTwo.players.get(bowlerNo).bowlerRun += 5;
            thisOver.append("5 ");
        } else if (v == btnSix) {
            teamOne.run += 6;
            teamOne.ball++;
            teamOne.players.get(strikerNo).ball++;
            teamOne.players.get(strikerNo).run += 6;
            teamOne.players.get(strikerNo).six++;

            teamTwo.players.get(bowlerNo).bowlerBall++;
            teamTwo.players.get(bowlerNo).bowlerRun += 6;
            thisOver.append("6 ");
        } else if (v == btnOut) {
            teamOne.wicket++;
            teamTwo.players.get(bowlerNo).wickets++;
            teamOne.ball++;
            teamTwo.players.get(bowlerNo).bowlerBall++;
            teamOne.players.add(new Player());
            thisOver.append("W ");
            strikerNo = teamOne.wicket + 1;
        } else if (v == btnNb) {
            teamOne.run++;
            teamTwo.players.get(bowlerNo).bowlerRun++;
            thisOver.append("Nb ");
        } else if (v == btnWd) {
            teamOne.run++;
            teamTwo.players.get(bowlerNo).bowlerRun++;
            thisOver.append("Wd ");
        } else if (v == btnBye) {
            teamOne.run++;
            teamTwo.players.get(bowlerNo).bowlerRun++;
            thisOver.append("1b ");
        } else if (v == btnLegBye) {
            teamOne.run++;
            teamTwo.players.get(bowlerNo).bowlerRun++;
            thisOver.append("1b ");
        } else if (v == tvStriker) {
            setNameDialog(tvStriker, teamOne.players.get(strikerNo).name);
        } else if (v == tvNonStriker) {
            setNameDialog(tvNonStriker, teamOne.players.get(nonStrikerNo).name);
        } else if (v == tvTeamOneName) {
            setNameDialog(tvTeamOneName, teamOne.name);
        } else if (v == tvBowlerName) {
            setNameDialog(tvBowlerName, teamTwo.players.get(bowlerNo).name);
        } else if (v == btnBowler) {
            playerListDialog(teamTwo);
        } else if (v == btnBatsman) {
            playerListDialog(teamOne);
        }

        if (teamTwo.players.get(bowlerNo).bowlerBall % 6 == 1) {
            char c = thisOver.charAt(thisOver.length() - 2);
            thisOver = new StringBuilder();
            thisOver.append(c + " ");
        }

        if (isTeamTwoInBatting) {
            int targetRun = teamTwo.run - teamOne.run + 1;
            int ballsRemain = totalBall - teamOne.ball;
            tvCommentary.setText(teamOne.name + " needs " + targetRun + " from " + ballsRemain + " balls to win");
        }

        teamOne.players.get(strikerNo).strikeRate();

        tvThisOver.setText(thisOver);

        tvStrikerScore.setText(teamOne.players.get(strikerNo).run + "(" + teamOne.players.get(strikerNo).ball + ")");
        tvStrikerFour.setText("4x" + teamOne.players.get(strikerNo).four);
        tvStrikerSix.setText("6x" + teamOne.players.get(strikerNo).six);
        tvStrikerStrikeRate.setText("str. " + String.format("%.2f", teamOne.players.get(strikerNo).strikeRate));

        tvNonStrikerScore.setText(teamOne.players.get(nonStrikerNo).run + "(" + teamOne.players.get(nonStrikerNo).ball + ")");
        tvNonStrikerFour.setText("4x" + teamOne.players.get(nonStrikerNo).four);
        tvNonStrikerSix.setText("6x" + teamOne.players.get(nonStrikerNo).six);
        tvNonStrikerStrikeRate.setText("str. " + String.format("%.2f", teamOne.players.get(nonStrikerNo).strikeRate));

        tvTeamOneScore.setText(teamOne.run + "/" + teamOne.wicket);
        tvTeamOneOver.setText(teamOne.over() + "." + teamOne.overBall());
        tvTeamOneRunRate.setText(String.format("%.2f", teamOne.runRate()));
        tvTeamTwoScore.setText(teamTwo.run + "/" + teamTwo.wicket);

        tvBowlerName.setText(teamTwo.players.get(bowlerNo).name);
        tvBowlerOver.setText(teamTwo.players.get(bowlerNo).over() + "." + teamTwo.players.get(bowlerNo).overBall());
        tvBowlerRun.setText(String.valueOf(teamTwo.players.get(bowlerNo).bowlerRun));
        tvBowlerEconomyRate.setText(String.format("%.2f", teamTwo.players.get(bowlerNo).economyRate()));
        tvBowlerWicket.setText(String.valueOf(teamTwo.players.get(bowlerNo).wickets));

        tvStriker.setText(teamOne.players.get(strikerNo).name);
        tvNonStriker.setText(teamOne.players.get(nonStrikerNo).name);
    }

    private void swapBatsman() {
        int temp = strikerNo;
        strikerNo = nonStrikerNo;
        nonStrikerNo = temp;
    }

    private void swapTeam() {
        Team temp = teamOne;
        teamOne = teamTwo;
        teamTwo = temp;
    }

    private void setNameDialog(final TextView view, String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText changeName = new EditText(this);
        changeName.setText(name);
        builder.setView(changeName);
        builder.setTitle("Set Name");
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (view == tvTeamOneName) {
                            teamOne.name = changeName.getText().toString();
                        } else if (view == tvStriker) {
                            teamOne.players.get(strikerNo).name = changeName.getText().toString();
                        } else if (view == tvNonStriker) {
                            teamOne.players.get(nonStrikerNo).name = changeName.getText().toString();
                        } else if (view == tvBowlerName) {
                            teamTwo.players.get(bowlerNo).name = changeName.getText().toString();
                        }
                        view.setText(changeName.getText().toString());
                    }
                });
        builder.setNeutralButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog simpleDialog = builder.create();
        simpleDialog.show();
    }

    private void playerListDialog(Team team) {

        String[] playerList = new String[team.players.size()];
        for (int i = 0; i < team.players.size(); i++) {
            playerList[i] = team.players.get(i).name;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Player List");
        builder.setCancelable(true);
        builder.setItems(playerList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                bowlerNo = which;
                tvBowlerName.setText(teamTwo.players.get(bowlerNo).name);
                tvBowlerOver.setText(teamTwo.players.get(bowlerNo).over() + "." + teamTwo.players.get(bowlerNo).overBall());
                tvBowlerRun.setText(String.valueOf(teamTwo.players.get(bowlerNo).bowlerRun));
                tvBowlerEconomyRate.setText(String.format("%.2f", teamTwo.players.get(bowlerNo).economyRate()));
                tvBowlerWicket.setText(String.valueOf(teamTwo.players.get(bowlerNo).wickets));
            }
        });
        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog mapTypeDialog = builder.create();
        mapTypeDialog.show();
    }

}