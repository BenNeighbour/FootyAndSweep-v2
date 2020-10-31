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

package com.footyandsweep.apisweepstakeengine.model;

import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import com.footyandsweep.apicommonlibrary.model.ticket.TicketCommon;
import com.footyandsweep.apicommonlibrary.model.user.UserCommon;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "sweepstake")
@AttributeOverrides({
  @AttributeOverride(
      name = "id",
      column = @Column(columnDefinition = "uuid", updatable = false, name = "id"))
})
public class Sweepstake extends SweepstakeCommon {

  private static final long serialVersionUID = -706809544736918166L;

  @Transient private List<TicketCommon> tickets;

  @Transient private UserCommon owner;
}
