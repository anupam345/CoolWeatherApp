/* eslint-disable */
import React from 'react'
import { Select, Form } from 'antd'
//var url1 = 'http://172.105.191.43:5000/download_template/'
const { Option } = Select
const downloadCSV = async (url, model, name) => {
  if (model === '') {
    alert('Please select a model')
  }
  url = url + model
  const response = await fetch(url)
  const data = await response.text()
  const blob = new Blob([data], { type: 'data:text/csv;charset=utf-8,' })
  const blobURL = window.URL.createObjectURL(blob)

  // Create new tag for download file
  const anchor = document.createElement('a')
  anchor.download = name
  anchor.href = blobURL
  anchor.dataset.downloadurl = ['text/csv', anchor.download, anchor.href].join(':')
  anchor.click()

  // Remove URL.createObjectURL. The browser should not save the reference to the file.
  setTimeout(() => {
    // For Firefox it is necessary to delay revoking the ObjectURL
    URL.revokeObjectURL(blobURL)
  }, 100)
}
class Inputer extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      models: [],
      selected: '',
    }
    this.onChange = this.onChange.bind(this)
  }
  componentWillMount = async () => {
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
  }

  onChange(e) {
    console.log(e)
    this.setState({ selected: e })
    console.log(this.state.selected)
  }
  render() {
    return (
      <div>
        <Form layout="inline">
          <Select
            showSearch
            style={{ width: 1000 }}
            placeholder="Search to Select Model"
            optionFilterProp="children"
            filterOption={(input, option) =>
              option.children.toLowerCase().indexOf(input.toLowerCase()) >= 0
            }
            filterSort={(optionA, optionB) =>
              optionA.children.toLowerCase().localeCompare(optionB.children.toLowerCase())
            }
            onChange={this.onChange}
          >
            {this.state.models.map(item => (
              <Option value={item} key={item}>
                {item}
              </Option>
            ))}
          </Select>
          <button
            type="button"
            onClick={() => downloadCSV(url1, this.state.selected, 'template.csv')}
            className="btn btn-success mt-1 mb-1"
          >
            Download
          </button>
        </Form>
      </div>
    )
  }
}

export default Inputer