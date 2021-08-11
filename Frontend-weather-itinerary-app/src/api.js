/* eslint-disable */
import axios from 'axios'
const urladd = 'http://localhost:8082/'

export var postItinerary = async (model,param) => {
    //let formData = new FormData()
        //formData.append('metrics-data', range)
        //formData.append('ccop_model_id', model)
    if (param ===''){
        axios.post(urladd+"itinerary",model)
        .then(response=>{
            console.log(response)
        })
        .catch(error=>{
            console.log(error)
        })
    }
    else{
        axios.put(urladd+"itinerary/"+param,model)
        .then(response=>{
            console.log(response)
        })
        .catch(error=>{
            console.log(error)
        })
    }
}

