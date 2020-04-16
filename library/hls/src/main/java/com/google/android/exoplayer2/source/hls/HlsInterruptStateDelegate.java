package com.google.android.exoplayer2.source.hls;

import androidx.annotation.NonNull;

/**
 * Delegate setting and querying the hls chunk download interrupt state
 */
public interface HlsInterruptStateDelegate {

  enum InterruptState {
    FINISHED,
    REQUESTED,
    TRIGGERED,
  }

  /**
   * @return the current state of the HLS chunk download interrupt process by default it's {@link
   * InterruptState#FINISHED} and should end with it too
   */
  @NonNull
  InterruptState getCancelState();

  /**
   * @param interruptState current state
   */
  void setCancelState(@NonNull InterruptState interruptState);
}

