import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Switch, Link } from "react-router-dom";
import TourMap from './TourMap';
import Home from './Home';

// API key of the google map
const GOOGLE_MAP_API_KEY = 'AIzaSyAlpa0Cy26gTkroY7rw8NJJhiSwD-aZMzA';

// load google map script
const loadGoogleMapScript = (callback) => {
  if (typeof window.google === 'object' && typeof window.google.maps === 'object') {
    callback();
  } else {
    const googleMapScript = document.createElement("script");
    googleMapScript.src = `https://maps.googleapis.com/maps/api/js?key=${GOOGLE_MAP_API_KEY}&libraries=geometry`;
    window.document.body.appendChild(googleMapScript);
    googleMapScript.addEventListener("load", callback);
  }
}

const App = () => {
  const [loadMap, setLoadMap] = useState(false);

  useEffect(() => {
    loadGoogleMapScript(() => {
      setLoadMap(true)
    });
  }, []);

  // return (
  //   <div className="App">
  //     <h4>Draw a line between two points on google maps in React - <a href="https://www.cluemediator.com">Clue Mediator</a></h4>
  //     {/* <Home>gfjgf</Home> */}
  //     {!loadMap ? <div>Loading...</div> : <GMap />}
  //     <br />
  //     <small><b>Note:</b> In order to make it work, you have to set the YOUR_GOOGLE_MAP_API_KEY in App.js file. </small>
  //   </div>
  // );

  return (
    <div>
      <Router >
        <div className="App">
          <nav className="navbar navbar-expand-md bg-dark navbar-dark">
            <div className="navbar-header display-5">
              <Link className="navbar-brand" to="/">PSA</Link>
            </div>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navData" aria-controls="navbarSupportedContent" aria-expanded="true" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div className='collapse navbar-collapse' id='navData'>
              <ul className="navbar-nav">
                <li className="nav-item">
                  <Link className="nav-link" to="/Christofides">Christofides</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/twoOpt">Two-Opt</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/threeOpt">Three-Opt</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/aco">Ant Colony Optimization</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/sa">Simualated Annealing</Link>
                </li>
              </ul>
            </div>
          </nav>          
          <Switch>
            <Route exact path="/" component={Home}></Route>
            <Route exact path="/Christofides" render={() => <TourMap data={"christofides"} />} />
            <Route exact path="/twoOpt" render={() => <TourMap data={"two-opt"} />} />
            <Route exact path="/threeOpt" render={() => <TourMap data={"three-opt"} />} />
            <Route exact path="/aco" render={() => <TourMap data={"aco"} />} />
            <Route exact path="/sa" render={() => <TourMap data={"sa"} />} />
          </Switch>
        </div>
      </Router>
    </div>
  );
}

export default App;
