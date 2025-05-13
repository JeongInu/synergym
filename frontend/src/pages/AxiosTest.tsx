import { useEffect, useState } from 'react';
import { testServer } from '../api/testApi';

const AxiosTest = () => {
    const [message, setMessage] = useState('');

    useEffect(() => {
        testServer()
            .then((data) => setMessage(data.message))
            .catch(() => setMessage('서버 연결 실패'));
    }, []);

    return <div>결과 : {message}</div>
};

export default AxiosTest;