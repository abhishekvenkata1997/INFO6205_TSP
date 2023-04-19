import React, { Component } from 'react';

class Home extends Component {
    state = {
            algorithm: ""
    };

    handleChange = (event) => {
        const target = event.target;
        const name = target.name;
        const value = target.value;
        this.setState({ [name]: value })
    }

    render() {
        return (
            <div className="container-fluid bg-light " style={{ height: "100vh" }}>
                <div className="row mt-50" >
                    <div className="col-md-6 offset-3 text-center" style={{ "marginTop": "200px" }}>
                        <pre>
                            <h4>INFO6205</h4>
                            <h3>Final Project</h3>
                            <h1 className="display-5">Travelling Salesman Problem</h1>
                            <div className="text-left display-6" style={{ "marginTop": "100px", "marginLeft": "550px" }}>
                                <p>Sai Veerendra Prathipati</p>
                                <p>Abhishek Venkata Tiruchunapalli</p>  
                                <p>Sreya Vallabhaneni</p>
                            </div>
                        </pre>
                        
                        
                    </div>
                </div>          
            </div>
          );
    }
}

export default Home;