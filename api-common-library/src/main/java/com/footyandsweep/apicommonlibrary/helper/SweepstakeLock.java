/*
 *   Copyright 2021 FootyAndSweep
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.footyandsweep.apicommonlibrary.helper;

import java.util.HashSet;
import java.util.Set;

public class SweepstakeLock {

  private static final Set<String> lockedUserKeys = new HashSet<>();
  private static final Set<String> lockedSweepstakeKeys = new HashSet<>();

  public static void userLock(String userKey) throws InterruptedException {
    synchronized (lockedUserKeys) {
      while (!lockedUserKeys.add(userKey)) {
        lockedUserKeys.wait();
      }
    }
  }

  public static void userUnlock(String userKey) {
    synchronized (lockedUserKeys) {
      lockedUserKeys.remove(userKey);
      lockedUserKeys.notifyAll();
    }
  }

  public static void lockSweepstake(String code) throws InterruptedException {
    synchronized (lockedSweepstakeKeys) {
      while (!lockedSweepstakeKeys.add(code)) {
        lockedSweepstakeKeys.wait();
      }
    }
  }

  public static void unlockSweepstake(String code) {
    synchronized (lockedSweepstakeKeys) {
      lockedSweepstakeKeys.remove(code);
      lockedSweepstakeKeys.notifyAll();
    }
  }
}
