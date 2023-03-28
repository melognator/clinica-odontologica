import React from 'react'
import Card from 'react-bootstrap/Card';
import Col from 'react-bootstrap/Col'; 
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import Button from 'react-bootstrap/Button';

const FormCreateComponent = ({ onSubmit, title, buttonLabel, formGroups, verOnChange, verLabel }) => {

    const isHidden = false;
    const colClasses = "mb-1";
    const labelClasses = "mb-1";
    const inputClasses = "pt-1 pb-1 ps-2";
    const inputStyle = {
        fontSize: "0.90rem"
    }

    const exampleFormGroups = [
        {
            "subTitle": "Datos del odontólogo",
            "inputs": [
                {
                    "type": "text",
                    "label": "Nombre",
                    "placeholder": "pepito",
                    "required": true,
                    "value": "",
                    "onChange": (e) => handler(e)
                }
            ]
        }
    ]

    // formGroups = exampleFormGroups

    const renderizarGroups = () => (
        formGroups.map((formGroup, index) => (
            <React.Fragment key={formGroup.key}>
                {formGroup.subtitle != "" && formGroup.subtitle != undefined ? 
                    <Card.Subtitle className={"mb-1 text-muted" + (index != 0 ? " mt-2" : "")}>{formGroup.subtitle}</Card.Subtitle>
                : ""}
                <Form.Group controlId={"form" + formGroup.key}>
                    <Row>
                        {renderizarInputs(formGroup)}
                    </Row>
                </Form.Group>
            </React.Fragment>
        ))
    )

    const renderizarInputs = (formGroup) => (
        formGroup.inputs.map(input => (
            <Col key={input.label} className={colClasses} lg={12} md={12} sm={12} xs={12}>
                <Form.Label className={labelClasses} visuallyHidden={isHidden}>{input.label}</Form.Label>
                {input.type != "select" ? 
                (
                    <Form.Control value={input.value} onChange={input.onChange} autoComplete="off" style={inputStyle} className={inputClasses} required={true} type={input.type} placeholder={input.placeholder} />
                ) : (
                    <Form.Select value={input.value} onChange={input.onChange} autoComplete="off" style={inputStyle} className={inputClasses} required={true}>
                        <option value="" disabled>{input.placeholder}</option>
                        {input.options}
                    </Form.Select>
                )}
            </Col>
        ))
    )

    return (
        <Row>
            <Col xl={4} lg={3} md={2} sm={0} xs={0}></Col>
            <Col xl={4} lg={6} md={8} sm={12} xs={12}>
                <Card>
                    <Card.Header>
                        <Card.Title className="p-1" style={{ margin: 0 }}>{title}</Card.Title>
                    </Card.Header>
                    <Card.Body>
                        <Form onSubmit={onSubmit}>
                            {renderizarGroups()}
                            <Form.Group controlId="formVer">
                                <Form.Check onChange={verOnChange} className="mt-1 mb-2" type="checkbox" label={verLabel} />
                            </Form.Group>
                            <Row>
                                <Col>
                                    <Button className="w-100" variant="primary" type="submit">
                                        {buttonLabel}
                                    </Button>
                                </Col>
                            </Row>
                        </Form>
                    </Card.Body>

                </Card>
            </Col>
            <Col xl={4} lg={3} md={2} sm={0} xs={0}></Col>
        </Row>
    )
}

export default FormCreateComponent