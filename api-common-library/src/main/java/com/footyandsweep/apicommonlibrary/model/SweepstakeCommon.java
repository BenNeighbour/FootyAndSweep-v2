/*
 *   Copyright 2020 FootyAndSweep
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

package com.footyandsweep.apicommonlibrary.model;

import com.footyandsweep.apicommonlibrary.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class SweepstakeCommon implements Serializable {

    private static final long serialVersionUID = -771315870335755392L;

    @Id
    @GeneratedValue
    private UUID id;

    @Transient
    private TransactionStatus transactionStatus = TransactionStatus.PENDING;

    private String name;

    @Version protected Integer version;

    private String code = generateSweepstakeCode();

    private SweepstakeStatus status = SweepstakeStatus.OPEN;

    private Boolean isPrivate;

    private UUID ownerId;

    @Transient
    private int sweepstakeListSize;

    private int minimumPlayers;

    private int maximumPlayerTickets;

    //    private SweepstakeEvent events;

    //    private SweepstakeType type;

    private BigDecimal stake;

    private int totalNumberOfTickets;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

    enum SweepstakeStatus {
        OPEN(0),
        ALLOCATED(1),
        CLOSED(2);

        private int code;

        SweepstakeStatus(int code) {
            this.code = code;
        }

        public int getSweepstakeStatus() {
            return this.code;
        }
    }

    private String generateSweepstakeCode() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String alphabetUppercase = alphabet.toUpperCase();
        String possibleNumbers = "0123456789";

        String randomString = alphabet + alphabetUppercase + possibleNumbers;
        SecureRandom random = new SecureRandom();

        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            int rndCharAt = random.nextInt(randomString.length());
            char rndChar = randomString.charAt(rndCharAt);

            sb.append(rndChar);
        }

        return sb.toString();
    }

}
