const Input = (props) => {
    const { id, placeholder = '', label = '', type = 'text', ...rest } = props;
    return (
        <div>
            <label htmlFor={id}>{label}</label>
            <input type={type} id={id} placeholder={placeholder} {...rest} />
        </div>
    );
};

export default Input;
