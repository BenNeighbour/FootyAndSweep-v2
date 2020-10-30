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

package com.footyandsweep.apiresultengine.model;

import com.footyandsweep.apicommonlibrary.model.ResultCommon;

import javax.persistence.*;

@Entity
@Table(name = "result")
@Inheritance(strategy = InheritanceType.JOINED)
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(columnDefinition = "uuid", updatable = false, name = "id"))
})
public class Result extends ResultCommon {

    private static final long serialVersionUID = -3355728133978299652L;

}
