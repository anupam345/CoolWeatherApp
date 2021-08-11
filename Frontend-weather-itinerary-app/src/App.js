// eslint-disable-next-line
import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import DatePicker from 'react-datepicker'
import 'react-datepicker/dist/react-datepicker.css'
import Table from 'rc-table';
import { postItinerary } from './api'
import axios from 'axios'
import Modaler from './Modaler'
const columns = [
	{
		title: 'City Name',
		dataIndex: 'city',
		key: 'city',
		width: 300,
	},
	{
		title: 'Country Code',
		dataIndex: 'code',
		key: 'code',
		width: 300,
	},
	{
		title: 'Date & Time',
		dataIndex: 'time',
		key: 'time',
		width: 300
	},

	{
		title: 'Temperature',
		dataIndex: 'temperature',
		key: 'temperature',
		width: 300,
	},
	{
		title: 'Clouds',
		dataIndex: 'clouds',
		key: 'clouds',
		width: 300,
	},
	{
		title: 'Weather',
		dataIndex: 'weather',
		key: 'weather',
		width: 300,
	},

];
class App extends Component {

	constructor(props) {
		super(props);
		this.state = {
			'config': {},
			//city
			value: '',
			// date
			selectedDate: new Date(),
			// data added in the table
			data: [],
			// saved itinerary name
			savedIti: "",
			// list of all itineraries data
			itineries: [],
			// all itineraries only name
			options: [],
			summary:'',
			show:false

		};
		this.tableData = []
		//this.listdata = {},
		//this.performSearch = this.performSearch.bind(this);
		this.filterDates = this.filterDates.bind(this);
	}

	componentDidMount() {
		var tempOptions = []
		axios.get("http://localhost:8082/itinerary")
			.then(response => {
				console.log(response)
				let res = response.data
				const allItineries = res.filter(val => val.id !== "").map(item => {
					console.log("item", item.id)
					return { value: item.id, label: item.id }
				})

				this.setState({ options: allItineries })
				this.setState({ itineries: response.data })
				console.log("item2", this.state.options)
			})
			.catch(error => {
				console.log(error)
			})


		console.log(this.state.options)
	}
	handleSubmit=(e)=> {
		e.preventDefault();
		var query = this.state.value;
		fetch('http://localhost:8082/weatherData/' + query).then(response => {
			return response.json();
		}).then(resData => {
			console.log(JSON.stringify(resData));
			if (!resData.list) {
				alert("Please check input");
			} else {

				const city = resData.city.name;
				var temp = []
				const country = resData.city.country;
				const list = this.filterDates(resData.list);
				Object.entries(list).map(([key, val]) => {
					let object = {
						"city": city,
						"time": val[1].dt_txt,
						"code": country,
						"temperature": val[1].main.temp + ' \u00b0C',
						"clouds": val[1].clouds.all + '%',
						"weather":val[1].weather[0].main,
						"description":val[1].weather[0].description
					};
					console.log(val[1].dt_txt);
					let flg = true;
					// To avoid duplicate values in table
					this.state.data.map(value => {
						console.log("city", value.city)
						if (value.city === city && value.time === val[1].dt_txt && flg) {
							flg = false
						}
					})
					if (this.state.data.length === 0 || flg) {
						temp.push(object);
					}
				});
				console.log(![...this.state.data] === [...temp])

				this.setState({ data: [...this.state.data, ...temp] })
			}

		});
	}
	filterDates(data) {
		let today = this.state.selectedDate;
		let dd = String(today.getDate()).padStart(2, '0');
		let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
		let yyyy = today.getFullYear();

		let date = yyyy + "-" + mm + "-" + dd
		let res = Object.entries(data).filter(([key, val]) => {
			const myArr = val.dt_txt.split(" ");
			const time = myArr[1].split(":")
			console.log(date === myArr[0] && 12 <= time[0] && time[0] <= 18)
			return date === myArr[0] && 12 <= time[0] && time[0] <= 18
		}
		)
		return res
	}

	// handleSubmit = (event) => {
	// 	this.setState({ value: event.target.value });
	// 	this.performSearch();

	// }
	reset = () => {
		this.setState({
			data: [],
			selectedDate: new Date(),
			value: "",
			savedIti: ""
		});


	}
	handleInputChange = (event) => {
		this.setState({ value: event.target.value });
	}

	handleDateChange = (date) => {
		console.log('this.state.date', this.state.date);
		this.setState({ selectedDate: date });
	}

	saveItinerary = (e) => {
		e.preventDefault();
		console.log(this.state.savedIti)
		let itineraryName = this.state.savedIti;
		let data = this.state.data;
		if (itineraryName === '' || data.length === 0) {
			alert("Missing parameters")
		}
		else {
			let opts = this.state.options;
			if (opts.some(item => itineraryName === item.value))
			{
				var param =itineraryName
			}
			else{
				param =''
				let newOptions = this.state.options;
				newOptions.push({ value: itineraryName, label: itineraryName });
				this.setState({ options: newOptions })
			}

			let apiData = {
				'id': itineraryName,
				'data': data
			}
			console.log(data)
			var res = postItinerary(apiData,param)
			alert("Saved")
		}
	}
	saveInputChnage = (e) => {
		this.setState({ savedIti: e.target.value });
	}
	handleChange = (e) => {
		this.setState({ data: [] })
		console.log(e.target.value)
		var tempOptions = []
		axios.get("http://localhost:8082/itinerary/" + e.target.value)
			.then(response => {
				console.log(response)
				let res = response.data.data
				if (res===undefined){
					this.setState({ data: [] })
				}
				else{
				this.setState({ data: res })
				}
				console.log("item2", this.state.options)
			})
			.catch(error => {
				console.log(error)
			})
		console.log(this.state.options)
	}
	generateSummary = (e) => {
		let itineraryName = this.state.savedIti;
		let data = this.state.data;
		let apiData = {
			'id': "summary",
			'data':data,
			
		};
		console.log(data)
		axios.post("http://localhost:8082/weatherData/generateSummary",apiData)
		.then(response => {
			console.log("response",response)
			this.setState({show:true,
			summary:response.data})
			})        
			.catch(error=>{
				console.log(error)
			})

		//console.log("result",res.data)

	}

	showStateModal = () => {
		this.setState({ show: true })
	  }
	
	hideStateModal = () => {
		this.setState({ show: false })
	  }

	render() {
		const { data } = this.state;
		//console.log("length",this.state.data.length)
		console.log("sds", this.state.options)
		return (<div className="App">
			<header className="App-header">
				<h1 className="App-title"> Weather Itinerary: A Travel Guide for your upcoming trip</h1>
			</header>
			<p className="App-intro">
				To get started, Please enter your City and travel dates
			</p>
			<div className="card-container">
			<form onSubmit={this.handleSubmit} className="" >
				<label>
					City:  <span>&nbsp;&nbsp;</span>
					<input className="input-box" type="text" placeholder='Pleae Enter City name' 
					style={{height:"15px"}}
					value={this.state.value} onChange={this.handleInputChange}
						required
					/>
					<br /><br />
				</label>
				<div>
					<label>
						Date:  <span>&nbsp;&nbsp;</span>
						<DatePicker selected={this.state.selectedDate}
							onChange={this.handleDateChange}
							dateFormat='yyyy-MM-dd'
							minDate={new Date()}
							maxDate={new Date(new Date().getTime() + (4 * 24 * 60 * 60 * 1000))}
							isClearable
							required
							/>
					</label><br /><br />
				</div>
				<input  className="input-button" type="submit" value="Submit" />
				<span>&nbsp;&nbsp;</span>
				<input className="input-button" type="submit" value="Reset" onClick={this.reset} />
				<br /><br />
				</form>
				<form>
				<div >
					<label> Itinerary: <span>&nbsp;</span>
						<input className="input-box" placeholder="Provide name to Itineary"
							value={this.state.savedIti}
							onChange={this.saveInputChnage}
						/>
						<span>&nbsp;&nbsp;</span>
						<input className="input-button" type="Submit" value="Save" 
						onClick={this.saveItinerary} required/>
					</label><br /><br />
				</div>	
				</form>
				<div>

					<label> Saved Itineraries: <span>&nbsp;</span>

						<select
							style={{ width: '200px', height: '22px' }}
							onChange={this.handleChange}
						>
							<option value="Select">Select</option>
							{this.state.options.map((dropVal) => <option value={dropVal.value}>{dropVal.label} </option>
							)}
						</select>
						<span>&nbsp;&nbsp;</span>
						{this.state.data.length > 0
							&& <button className="input-button" onClick={this.generateSummary}>Generate Summary</button>}
					</label>
				</div>
			</div>
			<br /><br />
			<div>
				<Table columns={columns} data={this.state.data} />
			</div>
			<br /><br />
			<Modaler show={this.state.show} handleClose={this.hideStateModal}>
			<div> {this.state.summary}</div>
		  </Modaler>			
		</div>
		);
	}
}
export default App;
