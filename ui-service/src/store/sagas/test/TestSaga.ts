import {call, put, takeLatest} from 'redux-saga/effects';
import * as testService from "../../../services/TestService";

function* fetchTest(action: any) {
    try {
        const user = yield call(testService.getTestThing, action.payload.test);
        yield put({type: "TEST_FETCH_SUCCEEDED", user: user});
    } catch (e) {
        yield put({type: "TEST_FETCH_FAILED", message: e.message});
    }
}

function* testSaga() {
    yield takeLatest("TEST_FETCH_PENDING", fetchTest);
}

export default testSaga;