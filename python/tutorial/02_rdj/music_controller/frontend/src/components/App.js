import React , {Component} from "react";
import { render } from "react-dom";
import ReactDOM from "react-dom/client";

export default class App extends Component {
    constructor(props) {
        super(props);
    }


    render() {
        return <h1>Niko Testing The React Code</h1>
    }
}

const appDiv = document.getElementById("app");
render(<App />, appDiv)