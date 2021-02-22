/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.exoplayer2.demo2;

import static com.google.android.exoplayer2.demo2.IntentUtil.TITLE;
import static com.google.android.exoplayer2.demo2.IntentUtil.URI_EXTRA;
import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import java.util.List;

/**
 * An activity that plays media using {@link SimpleExoPlayer}.
 */
public class PlayerActivity extends AppCompatActivity {

  // Activity lifecycle.

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView();

    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    Bundle arguments = new Bundle();
    List<MediaItem> mediaItemsFromIntent = IntentUtil.createMediaItemsFromIntent(getIntent());
    arguments.putParcelable(URI_EXTRA, getIntent().getData());

    String title = getIntent().getStringExtra(TITLE);
    IntentUtil.addToBundle(mediaItemsFromIntent, arguments, title);

    String[] split = title.split("-");
    String index = split[split.length - 1];

    int count = parseInt(index);
    for (int i = 0; i < count; i++) {
      arguments.putInt("ID", i);
      PlayerFragment playerFragment = PlayerFragment.getInstance(arguments);
      fragmentTransaction.add(R.id.linear_root, playerFragment, "PlayerFragment" + i);
    }
    fragmentTransaction.commit();

  }

  @Override
  public void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
//    releasePlayer();
//    releaseAdsLoader();
//    clearStartPosition();
    setIntent(intent);
  }


  @Override
  public void onRequestPermissionsResult(
      int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (grantResults.length == 0) {
      // Empty results are triggered if a permission is requested while another request was already
      // pending and can be safely ignored in this case.
      return;
    }
    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//      initializePlayer();
    } else {
//      showToast(R.string.storage_permission_denied);
      finish();
    }
  }

  // Activity input

//  @Override
//  public boolean dispatchKeyEvent(KeyEvent event) {
  // See whether the player view wants to handle media or DPAD keys events.
//    return playerView.dispatchKeyEvent(event) || super.dispatchKeyEvent(event);
//  }

  // Internal methods

  protected void setContentView() {
    setContentView(R.layout.player_activity);
  }


}
