import React from 'react';
import ReactDOM from 'react-dom';

import * as serviceWorker from './serviceWorker';
import App from './App';
import Root from 'Root';

ReactDOM.render(
    <Root>
        <App />
    </Root>,
    document.getElementById('root')
);

serviceWorker.unregister();
