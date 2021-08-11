/* eslint-disable */

import './modal.css'
import React from 'react'

const Modaler = ({ handleClose, show, children }) => {

  const showHideClassName = show ? 'modal display-block' : 'modal display-none'

  return (
    <div className={showHideClassName}>
      <section className="modal-main">
        <div style={{ justifyContent: 'center', alignItems: 'center', fontSize: '18px' }}>
          {children}
        </div>
        <div
          style={{
            display: 'flex',
            justifyContent: 'center',
            //alignItems: 'center',
            marginTop: '13%',
          }}
        >
          <button type="button" className="input-button" onClick={handleClose}>
            Close
          </button>
        </div>
      </section>
    </div>
  )
}
export default Modaler
