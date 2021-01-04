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

const express = require('express');
const http = require('http');
const path = require('path');

let app = express();

app.use(express.static(path.join(__dirname, 'build')));

app.get('/*', function(req, res) {
    res.sendFile(path.join(__dirname, 'build/index.html'), function(err) {
        if (err) {
            res.status(500).send(err)
        }
    })
})

const port = process.env.PORT || '3000';

app.set('port', port);

const server = http.createServer(app);

server.listen(port, () => console.log(`Running on ${port}`));